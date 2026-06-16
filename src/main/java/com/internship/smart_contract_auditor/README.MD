# Smart Contract Vulnerability Detection API 🛡️

## Overview
An AI-powered backend service built with Spring Boot that acts as an automated security auditor for Blockchain Smart Contracts. It utilizes the Google Gemini API to analyze raw Solidity code and identify critical vulnerabilities (e.g., Reentrancy, Integer Overflow) in real-time.

## Tech Stack
* **Backend:** Java 17, Spring Boot 3.x
* **AI Integration:** Google Gemini 1.5 Flash API
* **Tools:** Maven, Lombok, Postman

## Core Architecture
1. **Client** sends a POST request containing raw Solidity code.
2. **Controller (`AuditController`)** receives the payload and maps it to the `AuditRequest` DTO.
3. **Service (`AuditService`)** constructs a strict security-focused prompt and transmits the payload to the Gemini API.
4. **AI Engine** processes the code and returns a structured JSON response detailing the Risk Level, Vulnerability Type, and Suggested Fixes.

## API Endpoint Reference

### Analyze Contract
* **URL:** `/api/audit`
* **Method:** `POST`
* **Content-Type:** `application/json`

#### Request Body
```json
{
  "contractCode": "contract Vulnerable { ... }"
}

# Replace <YOUR_REPO_LINK> with the actual URL of your new GitHub repo
git remote add origin <YOUR_REPO_LINK>
git push -u origin main