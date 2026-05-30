# RepoLens Project Context

## Overview

RepoLens is an AI-powered codebase understanding platform that helps developers understand unfamiliar repositories quickly.

Users submit a GitHub repository URL.

The backend clones the repository, analyzes its structure, dependencies, architecture, APIs, and code relationships, then generates structured JSON that the frontend visualizes.

The platform also provides AI-generated explanations of modules, architecture decisions, and potential bottlenecks.

---

# High Level Flow

GitHub Repository URL

↓

Authentication & Authorization

↓

Repository Analysis Pipeline

↓

Repository Cloning

↓

File System Scanning

↓

Language Detection

↓

Dependency Analysis

↓

API Flow Detection

↓

Architecture Graph Generation

↓

Database Storage

↓

Frontend Visualization

↓

AI Explanations

---

# Tech Stack

Backend:

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* MySQL

Frontend:

* React
* Tailwind CSS

Future:

* OpenAI / Gemini
* Neo4j (optional)
* Graph Visualization Libraries

---

# Backend Architecture

Controller Layer

Responsibilities:

* Handle HTTP requests
* Validate inputs
* Return DTOs

Services:

* RepoAnalysisController
* AuthController
* HealthController

---

Service Layer

Responsibilities:

* Business logic
* Orchestration
* Data aggregation

Services:

* RepoAnalysisService
* GitCloneService
* FileScannerService
* DependencyAnalyzerService
* ApiFlowService
* AIExplanationService

---

Repository Layer

Responsibilities:

* Database operations

Repositories:

* RepoAnalysisRepository
* UserRepository

---

Entity Layer

Responsibilities:

* Persist application data

Entities:

* User
* RepoAnalysis

---

DTO Layer

Responsibilities:

* API contracts

DTOs:

* RepoRequestDTO
* RepoResponseDTO
* LoginRequestDTO
* RegisterRequestDTO
* AuthResponseDTO

---

# Current Feature

Repo Analysis

Status:
In Progress

---

Endpoint

POST /repo-analysis/analyze

Request:

{
"repoUrl": "https://github.com/vercel/next.js"
}

---

Flow

1. Receive repository URL

2. Clone repository

3. Scan files and folders

4. Detect languages

5. Analyze dependencies

6. Build architecture metadata

7. Save analysis

8. Return response

---

Response

{
"projectName": "next.js",
"repoUrl": "https://github.com/vercel/next.js",
"totalFiles": 1200,
"totalFolders": 230,
"folders": [
"app",
"components"
],
"languages": [
"JavaScript",
"TypeScript"
]
}

---

# RepoAnalysis Entity

Purpose:
Stores analysis results for a repository.

Fields:

id
Long

projectName
String

repoUrl
String

totalFiles
int

totalFolders
int

folders
List<String>

languages
List<String>

Future Fields:

dependencies
List<String>

apiRoutes
List<String>

architectureJson
String

aiSummary
String

createdAt
Timestamp

---

# GitCloneService

Purpose:
Clone GitHub repositories locally.

Input:

String repoUrl

Output:

File localRepository

Responsibilities:

* Clone repository
* Create temporary directory
* Return local path

Future:

* Handle private repositories
* Cleanup old repositories

---

# FileScannerService

Purpose:
Scan repository contents.

Input:

File repositoryDirectory

Output:

Repository metadata

Responsibilities:

* Count files
* Count folders
* Detect extensions
* Collect folder names

Returns:

totalFiles

totalFolders

folders

languages

---

# DependencyAnalyzerService

Purpose:
Build dependency relationships.

Input:

Repository directory

Output:

Dependency graph

Responsibilities:

* Parse imports
* Detect package dependencies
* Build module relationships

Returns:

{
"moduleA": [
"moduleB",
"moduleC"
]
}

---

# ApiFlowService

Purpose:
Detect application flow.

Input:

Repository directory

Output:

Flow graph

Responsibilities:

* Detect controllers
* Detect services
* Detect repositories
* Build request path chains

Example:

UserController

↓

UserService

↓

UserRepository

---

# AIExplanationService

Purpose:
Generate human readable explanations.

Input:

Architecture metadata

Output:

Text explanation

Example:

"Authentication module handles login,
JWT generation, authorization,
and user validation."

---

# Future Visualizations

Folder Tree

Dependency Graph

Module Graph

API Flow Graph

Architecture Diagram

Database Relationships

---

# Long Term Goal

Input:

GitHub Repository URL

Output:

Complete architectural understanding of the codebase.

RepoLens should answer:

* What does this project do?
* What technologies are used?
* How are modules connected?
* What are the important entry points?
* Which files are most critical?
* Where are bottlenecks likely to occur?
* How does request flow work?
* How should a new developer start exploring the codebase?
