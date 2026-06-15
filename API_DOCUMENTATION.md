# SIP & Mutual Fund Simulator Backend API Documentation

## Base URL

```http
/api/v1
```

---

# Authentication APIs

## Register User

### Endpoint

```http
POST /auth/register
```

### Request

```json
{
  "fullName": "Mayur Pawar",
  "email": "mayur@gmail.com",
  "password": "Password@123",
  "mobileNumber": "9876543210"
}
```

### Response

```json
{
  "success": true,
  "message": "User registered successfully",
  "data": {
    "userId": 1,
    "email": "mayur@gmail.com"
  }
}
```

---

## Login

### Endpoint

```http
POST /auth/login
```

### Request

```json
{
  "email": "mayur@gmail.com",
  "password": "Password@123"
}
```

### Response

```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "accessToken": "jwt-token",
    "refreshToken": "refresh-token"
  }
}
```

---

# User APIs

## Get Profile

### Endpoint

```http
GET /users/me
```

### Response

```json
{
  "success": true,
  "data": {
    "id": 1,
    "fullName": "Mayur Pawar",
    "email": "mayur@gmail.com",
    "mobileNumber": "9876543210"
  }
}
```

---

# Mutual Fund APIs

## Get All Funds

### Endpoint

```http
GET /funds
```

### Response

```json
{
  "success": true,
  "data": [
    {
      "fundId": 1,
      "fundName": "Parag Parikh Flexi Cap",
      "schemeCode": "122639",
      "latestNav": 72.55
    }
  ]
}
```

---

## Search Funds

### Endpoint

```http
GET /funds/search?keyword=parag
```

---

## Get Fund Details

### Endpoint

```http
GET /funds/{fundId}
```

### Response

```json
{
  "success": true,
  "data": {
    "fundId": 1,
    "fundName": "Parag Parikh Flexi Cap",
    "schemeCode": "122639",
    "latestNav": 72.55,
    "amc": "PPFAS"
  }
}
```

---

# SIP APIs

## Create SIP

### Endpoint

```http
POST /sips
```

### Request

```json
{
  "fundId": 1,
  "monthlyAmount": 5000,
  "sipDate": 5,
  "startDate": "2026-01-01"
}
```

### Response

```json
{
  "success": true,
  "message": "SIP created successfully",
  "data": {
    "sipId": 101,
    "status": "ACTIVE"
  }
}
```

---

## Get SIP Details

### Endpoint

```http
GET /sips/{sipId}
```

---

## Get User SIPs

### Endpoint

```http
GET /sips
```

### Response

```json
{
  "success": true,
  "data": [
    {
      "sipId": 101,
      "fundName": "Parag Parikh Flexi Cap",
      "monthlyAmount": 5000,
      "status": "ACTIVE"
    }
  ]
}
```

---

## Update SIP

### Endpoint

```http
PUT /sips/{sipId}
```

### Request

```json
{
  "monthlyAmount": 7000
}
```

---

## Pause SIP

### Endpoint

```http
PATCH /sips/{sipId}/pause
```

---

## Resume SIP

### Endpoint

```http
PATCH /sips/{sipId}/resume
```

---

## Cancel SIP

### Endpoint

```http
PATCH /sips/{sipId}/cancel
```

---

# Transaction APIs

## Get All Transactions

### Endpoint

```http
GET /transactions
```

### Response

```json
{
  "success": true,
  "data": [
    {
      "transactionId": 1,
      "fundName": "Parag Parikh Flexi Cap",
      "amount": 5000,
      "nav": 72.20,
      "unitsPurchased": 69.25,
      "investmentDate": "2026-06-01"
    }
  ]
}
```

---

## Get Transaction Details

### Endpoint

```http
GET /transactions/{transactionId}
```

---

# Portfolio APIs

## Portfolio Summary

### Endpoint

```http
GET /portfolio/summary
```

### Response

```json
{
  "success": true,
  "data": {
    "totalInvested": 120000,
    "currentValue": 135000,
    "profitLoss": 15000,
    "profitPercentage": 12.5,
    "xirr": 14.7
  }
}
```

---

## Holdings

### Endpoint

```http
GET /portfolio/holdings
```

### Response

```json
{
  "success": true,
  "data": [
    {
      "fundName": "Parag Parikh Flexi Cap",
      "units": 560.45,
      "currentNav": 74.30,
      "marketValue": 41640
    }
  ]
}
```

---

## Portfolio Growth

### Endpoint

```http
GET /portfolio/growth
```

### Response

```json
{
  "success": true,
  "data": {
    "labels": [
      "Jan",
      "Feb",
      "Mar"
    ],
    "values": [
      5000,
      10300,
      15800
    ]
  }
}
```

---

# XIRR APIs

## Calculate XIRR

### Endpoint

```http
GET /portfolio/xirr
```

### Response

```json
{
  "success": true,
  "data": {
    "xirr": 14.73
  }
}
```

---

# Dashboard APIs

## Dashboard Summary

### Endpoint

```http
GET /dashboard
```

### Response

```json
{
  "success": true,
  "data": {
    "activeSips": 4,
    "totalInvested": 150000,
    "currentValue": 170500,
    "profit": 20500,
    "xirr": 15.2
  }
}
```

---

# Statement APIs

## Download PDF Statement

### Endpoint

```http
GET /statements/pdf
```

### Response

```http
Content-Type: application/pdf
```

---

## Statement History

### Endpoint

```http
GET /statements
```

### Response

```json
{
  "success": true,
  "data": [
    {
      "statementId": 1,
      "generatedAt": "2026-06-01T10:00:00"
    }
  ]
}
```

---

# Admin APIs

## Refresh NAV Data

### Endpoint

```http
POST /admin/nav/refresh
```

### Response

```json
{
  "success": true,
  "message": "NAV data refreshed successfully"
}
```

---

## Trigger SIP Scheduler

### Endpoint

```http
POST /admin/scheduler/run
```

### Response

```json
{
  "success": true,
  "message": "SIP processing started"
}
```

---

# Audit APIs

## Activity Logs

### Endpoint

```http
GET /audit/logs
```

### Response

```json
{
  "success": true,
  "data": [
    {
      "action": "SIP_CREATED",
      "timestamp": "2026-06-10T12:30:00"
    }
  ]
}
```

---

# Standard API Response Format

```json
{
  "success": true,
  "message": "Operation successful",
  "data": {}
}
```

---

# Error Response Format

```json
{
  "success": false,
  "message": "Validation failed",
  "errors": [
    {
      "field": "monthlyAmount",
      "message": "Monthly amount must be greater than 500"
    }
  ]
}
```
