# PowerShell script to convert Java files to Kotlin
# This script will:
# 1. Find all Java files in the project
# 2. Create corresponding Kotlin files with proper package structure
# 3. Delete the original Java files

# Function to convert a Java file to Kotlin
function ConvertJavaToKotlin {
    param (
        [string]$javaFilePath
    )
    
    Write-Host "Converting $javaFilePath to Kotlin..."
    
    # Read the Java file content
    $javaContent = Get-Content -Path $javaFilePath -Raw
    
    # Extract package name
    $packageMatch = [regex]::Match($javaContent, 'package\s+([\w.]+);')
    $packageName = $packageMatch.Groups[1].Value
    
    # Create the Kotlin file path
    $kotlinFilePath = $javaFilePath -replace '\\java\\', '\kotlin\' -replace '\.java$', '.kt'
    
    # Create directory if it doesn't exist
    $kotlinDir = Split-Path -Path $kotlinFilePath -Parent
    if (-not (Test-Path -Path $kotlinDir)) {
        New-Item -Path $kotlinDir -ItemType Directory -Force | Out-Null
    }
    
    # Convert Java content to Kotlin (basic conversion)
    $kotlinContent = ConvertJavaContentToKotlin $javaContent
    
    # Write the Kotlin content to file
    Set-Content -Path $kotlinFilePath -Value $kotlinContent
    
    Write-Host "Created Kotlin file: $kotlinFilePath"
    
    # Return the paths for further processing
    return @{
        JavaPath = $javaFilePath
        KotlinPath = $kotlinFilePath
    }
}

# Function to convert Java content to Kotlin content
function ConvertJavaContentToKotlin {
    param (
        [string]$javaContent
    )
    
    # Extract package name
    $packageMatch = [regex]::Match($javaContent, 'package\s+([\w.]+);')
    $packageName = $packageMatch.Groups[1].Value
    $kotlinContent = "package $packageName`n`n"
    
    # Extract imports
    $importMatches = [regex]::Matches($javaContent, 'import\s+([\w.]+);')
    foreach ($match in $importMatches) {
        $import = $match.Groups[1].Value
        $kotlinContent += "import $import`n"
    }
    $kotlinContent += "`n"
    
    # Check if it's a record class
    $recordMatch = [regex]::Match($javaContent, 'public\s+record\s+(\w+)\s*\((.*?)\)')
    if ($recordMatch.Success) {
        $className = $recordMatch.Groups[1].Value
        $parameters = $recordMatch.Groups[2].Value
        
        # Convert parameters to Kotlin style
        $kotlinParameters = $parameters -replace '@Nullable\s+', '' -replace '@NonNull\s+', ''
        $kotlinParameters = $kotlinParameters -replace '(\w+)\s+(\w+)', '$2: $1'
        $kotlinParameters = $kotlinParameters -replace 'String', 'String'
        $kotlinParameters = $kotlinParameters -replace '@Nullable', ''
        $kotlinParameters = $kotlinParameters -replace 'String(\s*[,)])', 'String?$1'
        
        $kotlinContent += "data class $className(`n    $($kotlinParameters -replace ',', ',`n    ')`n)"
        return $kotlinContent
    }
    
    # Check if it's a regular class
    $classMatch = [regex]::Match($javaContent, 'public\s+(?:final\s+)?class\s+(\w+)')
    if ($classMatch.Success) {
        $className = $classMatch.Groups[1].Value
        
        # Extract class body
        $classBodyMatch = [regex]::Match($javaContent, 'class\s+\w+.*?\{(.*)\}', [System.Text.RegularExpressions.RegexOptions]::Singleline)
        $classBody = $classBodyMatch.Groups[1].Value
        
        # Convert class body to Kotlin style (simplified)
        $kotlinClassBody = $classBody -replace 'private\s+final', 'private val'
        $kotlinClassBody = $kotlinClassBody -replace 'private\s+static\s+final', 'private val'
        $kotlinClassBody = $kotlinClassBody -replace '@NonNull', ''
        $kotlinClassBody = $kotlinClassBody -replace '@Nullable', ''
        $kotlinClassBody = $kotlinClassBody -replace 'public\s+(\w+)\(', 'constructor('
        
        $kotlinContent += "class $className {`n$kotlinClassBody`n}"
        return $kotlinContent
    }
    
    # If not recognized, return original content with a comment
    return $kotlinContent + "// TODO: Manual conversion needed for this file`n" + $javaContent
}

# Main script execution

# Find all Java files in the project
$javaFiles = Get-ChildItem -Path "src\main\java" -Recurse -Filter "*.java"

# Convert each Java file to Kotlin
foreach ($javaFile in $javaFiles) {
    $result = ConvertJavaToKotlin $javaFile.FullName
    
    # Uncomment the line below to delete the original Java file after conversion
    # Remove-Item -Path $result.JavaPath -Force
}

Write-Host "Conversion completed. Please review the generated Kotlin files before deleting the original Java files."
Write-Host "After verification, you can delete the Java files or uncomment the Remove-Item line in the script."