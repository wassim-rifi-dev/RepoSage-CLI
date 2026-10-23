# RepoSage CLI

A command-line tool that helps developers quickly understand a GitHub repository: its technologies, structure and entry points.

Built with **Java 21** and **Spring Boot**.

> **Status:** early development — [`v0.2.0`](https://github.com/wassim-rifi-dev/RepoSage-CLI/releases/tag/V0.2.0).
> This release adds the `update` command to check for and install new versions. Analysis commands will come in future versions.

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
java -jar target/reposage-cli.jar --help
java -jar target/reposage-cli.jar --version
```

| Option            | Description                     |
|-------------------|---------------------------------|
| `-h`, `--help`    | Show help and available options |
| `-v`, `--version` | Show the installed version      |

### `update` command

Checks the latest release published on GitHub and compares it with the installed version.

```bash
reposage update --check   # only check whether a new version is available
reposage update           # check, then download and install the latest version
```

| Option          | Description                                     |
|-----------------|-------------------------------------------------|
| `-c`, `--check` | Only check for an update, without installing it |

When a new version is available, `reposage update` downloads the Windows installer (`.exe`) from the latest release and runs it silently.

> **Note:** automatic installation is currently supported on **Windows** only.

The command reports one of the following:

- the installed version is up to date;
- a new version is available (showing the installed and latest versions);
- no published release was found on GitHub;
- the GitHub rate limit was reached (try again later);
- a network error occurred (GitHub cannot be reached).

You can also pass options through Maven:

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments="--help"
./mvnw spring-boot:run -Dspring-boot.run.arguments="update --check"
```

## Releases

See the [Releases](https://github.com/wassim-rifi-dev/RepoSage-CLI/releases) page for version notes.