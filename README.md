# **exercise-1**

## Summary


Simple CLI tool to calculate elapsed days between two dates. 

## Date Format

YYYY-MM-DD

## Prerequisites

- Git
- Java 8
- Gradle 3+
- Linux/MacOS/Windows(Cygwin)

## Installation

```
$git clone https://github.com/mattshen/exercise-1
$cd exercise-1
$./gradlew build test
```

Binary distribution would be available in `${projectDir}/build/distributions` after building

## Running Instructions (with source code)

#### example 1
```
./gradlew run -q
```

#### example 2
```
./gradlew run -q -Dexec.args="1983-06-02 1983-06-22"
```

