# 🩺 個人健檢查詢系統 (Personal Health Check System)

## 📘 專案簡介
這是一個基於 **Spring Boot** 框架所開發的簡易健康查詢系統。  
使用者需 **至少輸入身分證字號**，即可查詢個人健檢資料，並支援下載報告的 **PDF 檔案**。  

前端使用 **Thymeleaf** 作為模板引擎，  
後端透過 **Spring Data JPA** 操作 **MySQL** 資料庫。

https://github.com/user-attachments/assets/7d89bcd4-31f3-4ed4-9b93-cd7135fe65a1

PDF檔案下載範本：
[RPT_2.pdf](https://github.com/user-attachments/files/22945562/RPT_2.pdf)

---

## 🧩 功能說明
1. **健檢資料查詢**  
   - 使用者輸入身分證字號後查詢健檢結果。
   - 可透過日期與報告類型進行篩選。
2. **PDF 報告下載**  
   - 查詢結果可生成 PDF 報告並下載。
3. **錯誤處理機制**  
   - 若查無資料，系統會顯示提示訊息。
4. **前端驗證**  
   - 驗證身分證格式與空白輸入。
---

## ⚙️ 系統架構

| 層級 | 技術 | 說明 |
|------|------|------|
| 前端 (View) | Thymeleaf, Bootstrap | 提供查詢介面、查詢結果頁與報告下載功能 |
| 後端 (Controller / Service) | Spring Boot (Spring MVC) | 接收查詢請求、整合邏輯 |
| 資料庫層 (Repository) | MySQL + Spring Data JPA | 儲存使用者與健檢資料 |
| 報告生成 | iText5/openpdf | 將健檢結果輸出成 PDF |

---

## 🗄️ 資料庫結構

| 資料表名稱 | 欄位名稱 | 型別 | 說明 |
|-------------|-----------|------|------|
| **patients** | `patient_id` | INT (PK) | 使用者id |
|  | `national_id` | VARCHAR(20) | 身分證字號 |
|  | `first_name` | VARCHAR(45) | 使用者姓名 |
|  | `last_name` | VARCHAR(45) | 出生日期 |
|  | `email` | VARCHAR(45) | 信箱 |
|  | `birth_date` | DATE | 出生日期 (M/F) |
|  | `gender` | ENUM('M', 'F') | 性別 (M/F) |
|  | `created_at` | DATETIME | 建立時間 |
| **report** | `report_id` | BIGINT (PK) | 報告id |
|  | `patient_id` | INT (FK → patient.patient_id) | 使用者id |
|  | `report_date` | VARCHAR(10) | 報告日期 |
|  | `report_type` | ENUM('MEDICAL','FINANCIAL', 'GENERAL', 'AUDIT') DEFAULT 'MEDICAL' | 報告類型 |
|  | `report_status` | ENUM('PENDING','IN_PROGRESS', 'COMPLETED', 'CANCELLED') DEFAULT 'MEDICAL' | 報告狀態 |
|  | `summary` | TEXT | 報告摘要 |
|  | `created_at` | DATETIME | 建立時間 |
|  | `updated_at` | DATETIME | 建立時間 |
| **report_basic_check** | `basic_check_id` | INT (PK) | 基本報告id |
|  | `report_id` | BIGINT (FK → report.report_id) | 對應報告id |
|  | `height` | DECIMAL(5,2) | 身高 |
|  | `weight` | DECIMAL(5,2) | 體重 |
|  | `waist` | DECIMAL(5,2) | 腰圍 |
|  | `vision_left` | DECIMAL(4,2) | 左視力 |
|  | `vision_right` | DECIMAL(4,2) | 右視力 |
|  | `color_blind` | BOOLEAN | 是否為色盲 |
|  | `hearing_left` | VARCHAR(50) | 左耳聽力 |
|  | `hearing_right` | VARCHAR(50) | 右耳聽力 |
|  | `blood_pressure` | VARCHAR(50) | 血壓 |
|  | `history` | TEXT | 紀錄 |
| **report_urine_test** | `urine_id` | INT (PK) | 尿液報告id |
|  | `report_id` | BIGINT (FK → report.report_id) | 對應報告id |
|  | `protein` | VARCHAR(50) | 尿蛋白 (陰性/陽性/數值) |
|  | `occult_blood` | VARCHAR(50) | 尿潛血 (陰性/陽性/數值) |
| **report_liver_kidney** | `lk_id` | BIGINT (PK) | 肝腎報告id |
|  | `report_id` | BIGINT (FK → report.report_id) | 對應報告id |
|  | `alt` | DECIMAL(6,2) | 血清丙胺酸轉胺酶 (ALT) |
|  | `creatinine` | DECIMAL(6,2) | 肌酸酐 |
|  | `cholesterol_total` | DECIMAL(6,2) | 總膽固醇 |
|  | `triglyceride` | DECIMAL(6,2) | 三酸甘油酯 |
|  | `hdl` | DECIMAL(6,2) | 高密度脂蛋白膽固醇 |
|  | `fasting_glucose` | DECIMAL(6,2) | 空腹血糖 |
| **report_blood_test** | `lk_id` | BIGINT (PK) | 血液報告id |
|  | `report_id` | BIGINT (FK → report.report_id) | 對應報告id |
|  | `hemoglobin` | DECIMAL(5,2) | 血色素 |
|  | `wbc_count` | DECIMAL(10,2) | 白血球數 |

---

## 🏗️ 專案結構
<img width="406" height="482" alt="截圖 2025-10-15 下午6 02 45" src="https://github.com/user-attachments/assets/5981d168-f5f3-432d-8300-279cec81a4e8" />

---

## 🧰 補充項目

| 項目 | 說明 | 建議工具 |
|------|------|-----------|
| ✅ **欄位驗證** | 確保身分證格式正確 | `spring-boot-starter-validation` |
| ✅ **例外處理** | 查無資料顯示友善訊息 |  |
| ✅ **Session 管理** | 避免重複查詢 | Spring Session (可選) |
| ✅ **PDF 樣式** | 加入 Logo、表格邊框 | iText5/openpdf + 自訂樣板 |
| ❎ **日誌紀錄** | 紀錄查詢/下載行為 | SLF4J + Logback | 
| ❎ **容器化部署** | 方便展示與測試 | Docker Compose (含 MySQL) |
---

## 🚀 執行方式

```bash
# 匯入專案後
mvn spring-boot:run

# 伺服器啟動預設於
http://localhost:8080

# 畫面導向spring security預設的登入頁，
Username： user
Password： 請見終端機顯示資訊(範例：Using generated security password: 3035ee18-0e68-4a56-a85b-9418fa88eeb2**)

# 登入成功進入查詢畫面
