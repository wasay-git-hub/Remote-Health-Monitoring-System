Remote Health Monitoring System (RHMS)
Core Features
1.	User Management
o	Patient registration & profile management
o	Doctor registration & appointment control
o	Administrator oversight
2.	Health Monitoring
o	Vital sign tracking (heart rate, BP, oxygen, temp)
o	Emergency alerts for abnormal values
o	Medical history/prescription records
3.	Communication
o	Secure doctor-patient chat
o	Email/SMS notifications
o	Google Meet video consultations
4.	Appointment System
o	Request/approval workflow
o	Automated reminders
o	Status tracking (Pending/Approved/Rejected)
________________________________________
Class Structure
1. User Classes
Class	Purpose	Key Attributes/Methods
Users	Base class	id, name, email, contact
getName(), getEmail()
Patient	Patient entity	age, medicalHistory
requestAppointment(), viewMedicalHistory()
Doctor	Doctor entity	approveAppointment(), prescribeMedication()
Administrator	Admin controls	addPatient(), addDoctor()
________________________________________
2. Health Monitoring
Class	Purpose	Key Functionality
VitalSign	Stores health metrics	heart_rate, oxygen_level
toString()
VitalsDatabase	Vital sign storage	addVital(), retrieveVital()
EmergencyAlert	Critical value detector	checkVitals(), triggerAlert()
MedicalHistory	Patient records	addPrescription(), displayHistory()
________________________________________
3. Appointment System
Class	Purpose	Key Methods
Appointment	Appointment entity	date_of_appointment, status
AppointmentManager	Handles scheduling	createAppointment(), handleRequest()
ReminderService	Automated reminders	scheduleAppointmentReminder()
________________________________________
4. Communication System
Class	Purpose	Key Features
EmailNotification	Email alerts	SMTP integration
sendNotification()
SMSNotification	Text alerts	Phone validation
sendNotification()
ChatServer	Message handling	Message history storage
sendMessage()
ChatClient	Chat interface	displayChat(), sendMessage()
VideoCall	Video consultations	Google Meet integration
generateMeetingId()
________________________________________
5. Emergency System
Class	Purpose	Key Workflow
PanicButton	Manual emergency trigger	press() → Sends email alert
NotificationService	Alert dispatcher	sendEmailAlert(), sendSMSAlert()
________________________________________
Key Functionality Workflows
Appointment Flow
1.	Patient requestAppointment()
2.	AppointmentManager createAppointment()
3.	Doctor approveAppointment()/rejectAppointment()
4.	ReminderService sends notifications
Emergency Detection
1.	VitalsDatabase adds new readings
2.	EmergencyAlert.checkVitals() detects anomalies
3.	Triggers EmailNotification.sendNotification()
4.	PanicButton.press() sends manual alerts
Chat System
1.	ChatClient sends message via ChatServer
2.	Messages stored with timestamp
3.	Recipient views history via displayChat()
________________________________________
Dependencies
•	JavaMail API (Email notifications)
•	Java Activation Framework (MIME support)
•	Google Meet (Video calls)
•	Desktop API (Browser integration)

