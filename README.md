Remote Health Monitoring System (RHMS)

Core Features

User Management

o Patient registration & profile management

o Doctor registration & appointment control

o Administrator oversight

Health Monitoring

o Vital sign tracking (heart rate, BP, oxygen, temp)

o Emergency alerts for abnormal values

o Medical history/prescription records

Communication

o Secure doctor-patient chat

o Email/SMS notifications

o Google Meet video consultations

Appointment System

o Request/approval workflow

o Automated reminders

o Status tracking (Pending/Approved/Rejected)

Class Structure

User Classes

Class Purpose Key Attributes/Methods

Users Base class id, name, email, contact

getName(), getEmail()

Patient Patient entity age, medicalHistory

requestAppointment(), viewMedicalHistory()

Doctor Doctor entity approveAppointment(), prescribeMedication()

Administrator Admin controls addPatient(), addDoctor()

Health Monitoring

Class Purpose Key Functionality

VitalSign Stores health metrics heart_rate, oxygen_level

toString()

VitalsDatabase Vital sign storage addVital(), retrieveVital()

EmergencyAlert Critical value detector checkVitals(), triggerAlert()

MedicalHistory Patient records addPrescription(), displayHistory()

Appointment System

Class Purpose Key Methods

Appointment Appointment entity date_of_appointment, status

AppointmentManager Handles scheduling createAppointment(), handleRequest()

ReminderService Automated reminders scheduleAppointmentReminder()

Communication System

Class Purpose Key Features

EmailNotification Email alerts SMTP integration

sendNotification()

SMSNotification Text alerts Phone validation

sendNotification()

ChatServer Message handling Message history storage

sendMessage()

ChatClient Chat interface displayChat(), sendMessage()

VideoCall Video consultations Google Meet integration

generateMeetingId()

Emergency System

Class Purpose Key Workflow

PanicButton Manual emergency trigger press() → Sends email alert

NotificationService Alert dispatcher sendEmailAlert(), sendSMSAlert()

Key Functionality Workflows

Appointment Flow

Patient requestAppointment()

AppointmentManager createAppointment()

Doctor approveAppointment()/rejectAppointment()

ReminderService sends notifications

Emergency Detection

VitalsDatabase adds new readings

EmergencyAlert.checkVitals() detects anomalies

Triggers EmailNotification.sendNotification()

PanicButton.press() sends manual alerts

Chat System

ChatClient sends message via ChatServer

Messages stored with timestamp

Recipient views history via displayChat()

Dependencies

• JavaMail API (Email notifications)

• Java Activation Framework (MIME support)

• Google Meet (Video calls)

• Desktop API (Browser integration)
