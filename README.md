# Remote Health Monitoring System (RHMS)

## ⭐ Core Features

### 1. User Management
- *Patient registration & profile management*
- *Doctor registration & appointment control*
- *Administrator oversight*

### 2. Health Monitoring
- *Vital sign tracking (heart rate, BP, oxygen, temp)*
- *Emergency alerts for abnormal values*
- *Medical history/prescription records*

### 3. Communication
- *Secure doctor-patient chat*
- *Email/SMS notifications*
- *Google Meet video consultations*

### 4. Appointment System
- *Request/approval workflow*
- *Automated reminders*
- *Status tracking (Pending/Approved/Rejected)*

---

## 🧱 Class Structure

### 1. User Classes

| **Class**       | **Purpose**        | **Key Attributes/Methods**                         |
|-----------------|--------------------|----------------------------------------------------|
| `Users`         | Base class          | `id`, `name`, `email`, `contact`<br>`getName()`, `getEmail()` |
| `Patient`       | Patient entity      | `age`, `medicalHistory`<br>`requestAppointment()`, `viewMedicalHistory()` |
| `Doctor`        | Doctor entity       | `approveAppointment()`, `prescribeMedication()`    |
| `Administrator` | Admin controls      | `addPatient()`, `addDoctor()`                      |

---

### 2. Health Monitoring

| **Class**         | **Purpose**             | **Key Functionality**                             |
|-------------------|--------------------------|---------------------------------------------------|
| `VitalSign`       | Stores health metrics    | `heart_rate`, `oxygen_level`<br>`toString()`      |
| `VitalsDatabase`  | Vital sign storage       | `addVital()`, `retrieveVital()`                   |
| `EmergencyAlert`  | Critical value detector  | `checkVitals()`, `triggerAlert()`                 |
| `MedicalHistory`  | Patient records          | `addPrescription()`, `displayHistory()`           |

---

### 3. Appointment System

| **Class**            | **Purpose**           | **Key Methods**                                   |
|----------------------|------------------------|---------------------------------------------------|
| `Appointment`        | Appointment entity     | `date_of_appointment`, `status`                  |
| `AppointmentManager` | Handles scheduling     | `createAppointment()`, `handleRequest()`          |
| `ReminderService`    | Automated reminders    | `scheduleAppointmentReminder()`                   |

---

### 4. Communication System

| **Class**           | **Purpose**              | **Key Features**                                  |
|---------------------|---------------------------|---------------------------------------------------|
| `EmailNotification` | Email alerts              | *SMTP integration*, `sendNotification()`          |
| `SMSNotification`   | Text alerts               | *Phone validation*, `sendNotification()`          |
| `ChatServer`        | Message handling          | *Message history storage*, `sendMessage()`        |
| `ChatClient`        | Chat interface            | `displayChat()`, `sendMessage()`                  |
| `VideoCall`         | Video consultations       | *Google Meet integration*, `generateMeetingId()`  |

---

### 5. Emergency System

| **Class**           | **Purpose**                | **Key Workflow**                                  |
|---------------------|-----------------------------|---------------------------------------------------|
| `PanicButton`       | Manual emergency trigger    | `press()` → *Sends email alert*                   |
| `NotificationService` | Alert dispatcher          | `sendEmailAlert()`, `sendSMSAlert()`              |

---

## 🔁 Key Functionality Workflows

### ✅ Appointment Flow
1. `Patient.requestAppointment()`
2. `AppointmentManager.createAppointment()`
3. `Doctor.approveAppointment()` / `rejectAppointment()`
4. `ReminderService` sends notifications

### 🚨 Emergency Detection
1. `VitalsDatabase` adds new readings
2. `EmergencyAlert.checkVitals()` detects anomalies
3. Triggers `EmailNotification.sendNotification()`
4. `PanicButton.press()` sends manual alerts

### 💬 Chat System
1. `ChatClient.sendMessage()` via `ChatServer`
2. Messages stored with *timestamp*
3. Recipient views history via `displayChat()`

---

## 📦 Dependencies

- **JavaMail API** *(Email notifications)*
- **Java Activation Framework** *(MIME support)*
- **Google Meet** *(Video calls)*
- **Desktop API** *(Browser integration)*


