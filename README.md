# RepoSage CLI

A command-line tool that helps developers quickly understand a GitHub repository: its technologies, structure and entry points.

Built with **Java 21** and **Spring Boot**.

> **Status:** early development — [`v0.1.0`](https://github.com/wassim-rifi-dev/RepoSage-CLI/releases/tag/V0.1.0).
> This release sets up the project foundation. Analysis commands will come in future versions.

## Requirements

- **Java 21** or later
- Maven is not required: the project includes the Maven Wrapper (`mvnw` / `mvnw.cmd`)

## Installation

### Option 1 — Installer

1. Download the installer from the [Releases](https://github.com/wassim-rifi-dev/RepoSage-CLI/releases) page.
2. Run it and follow the installation steps.
3. Open a new terminal and check that it works:

```bash
reposage --version
reposage --help
```

### Option 2 — From source

```bash
git clone https://github.com/wassim-rifi-dev/RepoSage-CLI
cd RepoSage-CLI
```

## Build

```bash
# Linux / macOS
./mvnw clean package

# Windows
mvnw.cmd clean package
```

## Run tests

```bash
./mvnw test
```

## Run

```bash
./mvnw spring-boot:run
```

## Usage

After building, run the JAR with one of the available options:

```bash
java -jar target/reposage-cli-0.0.1.jar --help
java -jar target/reposage-cli-0.0.1.jar --version
```

| Option            | Description                    |
|-------------------|--------------------------------|
| `-h`, `--help`    | Show help and available options |
| `-v`, `--version` | Show the installed version     |

You can also pass options through Maven:

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments="--help"
```

## Releases

See the [Releases](https://github.com/wassim-rifi-dev/RepoSage-CLI/releases) page for version notes.