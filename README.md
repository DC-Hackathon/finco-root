# FINCO
Welcome to the project **FINCO**.

The goal of this project is to find complex data from different products like sonata and Distributions or any other product.
This project takes query string from the user and clean , stem and tokenize the string as a part of data cleaning and then label data on basis of Product.
It further tells us about the intent of query and finally returns data on basis of intent. 

The Components feature:
- Rich user interfaces
- Flexible theming facility with support i.e. primary , secondary / accent color.
- Swagger UI as a part of development.

**Contents**
<!-- prettier-ignore-start -->
<!-- TOC -->
- [1. Architecture]
- [2. Workspace Setup]
    - [2.1. Development tools]
    - [2.2. Preparing source directory]
    - [2.3. Importing project into IDEs]
- [4. Integration]
- [5. Security]
- [6. Cucumber or Cypress Development]
<!-- /TOC -->
<!-- prettier-ignore-end -->

### 1. Architecture
The following is the high level architecture for Distributions Micro-service and its integration with Other Systems.

![img.png](/readme.images/archi_dia.jpg)

### 2. Workspace setup

#### 2.1. Development tools

Please install these applications before setting up the workspace. To ease the setup, we usually install the development
tools via a system package manager, such as DKPG, RPM and Pacman on Linux, and Chocolatey on Windows. For your
reference, in each item of the following list of tools, there will be a Chocolatey installation command. Please also
refer to [the document site of Chocolatey](https://chocolatey.org/install) for more information on setting up the
package manager.
- Install [Java](https://www.oracle.com/java/technologies/downloads/) on your local machine and add it to `path`.
- Install [Maven](https://maven.apache.org/download.cgi) on your local machine and add it to `path`.
- Install Node JS
    - [NodeJS LTS](https://nodejs.org/en/download/)
  ```bash
  choco install nodejs-lts
  ```
- Install Yarn
    - [Yarn](https://yarnpkg.com/getting-started/install)
  ```bash
  choco install yarn
  ```
- Install Visual Studio
    - [Visual Studio Code](https://code.visualstudio.com/docs/setup/setup-overview) for frontend development
  ```bash
  choco install vscode
  ```
- Install IntelliJ / Eclipse
    - [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) or [Eclipse](https://www.eclipse.org/downloads/) for backend
      development
  ```bash
  choco install intellijidea-community
  ```
- Install postgres and create a user.
    - [Postgres](https://www.postgresql.org/download/)
  ```bash
  choco install postgresql
  ```
- Make sure you have configured `~/.m2/settings.xml` file on your local.
- Set up these Environment Variables on your local machine.

<a id="markdown-32-preparing-source-directory" name="32-preparing-source-directory"></a>
### 2.2. Preparing source directory

Clone the [finco git repository](hhttps://github.com/DC-Hackathon/finco-root) to
your workspace, by running the following command in the terminal.

```bash
git clone https://github.com/DC-Hackathon/finco-root.git
```

Change the current working directory (CWD) of the sell to `finco-root` that was newly created by the `git clone`
command. It will be the workspace directory that all CLI commands should run in.


When running a Maven command you can add the flag `-nsu` to skip snapshot library updates to avoid unnecessary
downloading of snapshot dependencies.

<a id="markdown-33-importing-project-into-ides" name="33-importing-project-into-ides"></a>
### 2.3. Importing project into IDEs

- Start Intellij IDEA and run the `Import Project` wizard. Select the git repository directory and use `Maven Importer`
  to create the workspace.

- To start development with VS Code, use the `File` menu to open the folder of the git repository.

<a id="markdown-4-project-structure" name="4-project-structure"></a>

### 3. Project structure

    finco-root/ - workspace
      ...
        finco-web/  ----- web sub-module
          src/
            app/            ----- frontend source folder
          ...
        finco-service/      ----- api sub-module
          src/
            main/           ----- backend source folder
          ...
        finco-nlp/          ----- NLP sub-module
          ...
        ...


