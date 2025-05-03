import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.awt.Desktop;
import java.net.URI;

class Users{
    protected int id;
    protected String name;
    protected String email;
    protected String contact;
    Users(){};
    Users(int id,String name,String email,String contact){
        this.id=id;
        this.name=name;
        this.email=email;
        this.contact=contact;
    }

    public String getName() {
       return name;
    }
    public String getContact() {
        return contact;
    }
    public String getEmail() {
        return email;
    }
    public int getId() {
        return id;
    }
}

class Patient extends Users{
     private int age;
     private Doctor doc;
     private boolean request;
     private String feedback;
     private MedicalHistory medicalHistory;
     
     Patient(Doctor doc,int id,String name,String email,String contact,int age){
         super(id, name, email, contact);
         this.doc=doc;
         this.age=age;
         this.medicalHistory = new MedicalHistory();
        }
     
     public void requestAppointment(){
          this.request=true;
     }

    public Doctor getDoc() {
        return doc;
    }

    public boolean isRequest() {
        return request;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void addPrescription(Prescription prescription) {
        this.medicalHistory.addPrescription(prescription);
    }

    public void viewMedicalHistory() {
        this.medicalHistory.displayHistory();
    }

    public int getAge() {
        return age;
    }
    public String getName(){
         return name;
    }
    public int getId(){
         return id;
    }
    public String getContact(){
         return contact;
    }
    public String getFeedback() {
        return feedback;
    }
}

class Doctor extends Users{

     Doctor(int id,String name,String email,String contact){
         super(id, name, email, contact);
     }
    public void approveAppointment(Appointment a){
        a.status = "Approved";
    }
    public void rejectAppointment(Appointment a){
        a.status = "Rejected";                              
    }
    public void giveFeedback(Patient p,String a){
        p.setFeedback(a);
    }
    public void prescribeMedication(Patient p, String medication, String dosage, String schedule) {
        Prescription prescription = new Prescription(medication, dosage, schedule);
        p.addPrescription(prescription);
    }
    public void viewPatientData(Patient p, VitalsDatabase vb){
        System.out.println("Name: " + p.getName() + " Age: " + p.getAge() + " contact: " + p.getContact());
        Object o = vb.retrieveVital(p);
        System.out.println(o);
    }
    public String getName(){
        return name;
    }
}

class Administrator extends Users{
    Administrator(int id,String name,String email,String contact){
        super(id, name, email, contact);
    }

    ArrayList<Patient> Array_Patient =new ArrayList<>();
    ArrayList<Doctor> Array_Doctor =new ArrayList<>();

    public void addPatient(Patient p){
        Array_Patient.add(p.id-1,p);
    }
    public void addDoctor(Doctor d){
        Array_Doctor.add(d.id-1,d);
    }
}

class VitalSign{
    private String heart_rate;
    private String oxygen_level;
    private String blood_pressure;
    private String temperature;
    VitalSign(){};
    VitalSign(String heart_rate,String oxygen_level,String blood_pressure,String temperature){
        this.blood_pressure=blood_pressure;
        this.oxygen_level=oxygen_level;
        this.temperature=temperature;
        this.heart_rate=heart_rate;
    }
    public String toString(){
        return "Heart rate: " + heart_rate + " bpm " + " Oxygen Level: " + oxygen_level + " BP: " + blood_pressure + " Temperature: " + temperature;
    }
}

class VitalsDatabase{
    ArrayList <VitalSign> Array_VitalSign = new ArrayList<>();

    public void addVital(VitalSign v,Patient p){
            Array_VitalSign.add(p.id-1,v);
    }
    public VitalSign retrieveVital (Patient p){
        for (int i = 0 ; i < Array_VitalSign.size(); i++){
            if (p.id==i+1){
                VitalSign v = Array_VitalSign.get(i);
                return v;
            }
        }
        return null;
    }
}

class Appointment {
    private Date date_of_appointment = new Date();
    private Doctor doctor;
    private Patient patient;
    public String status;
 
    Appointment(Doctor var1, Patient var2, String var3) {
       this.doctor = var1;
       this.patient = var2;
       this.status = var3;
    }

    public Date getDate_of_appointment() {
        return date_of_appointment;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }
}

 class AppointmentManager {
    private String name;
 
    AppointmentManager(String name) {
       this.name = name;
    }
 
    public Object handleRequest(Patient p) {
       if (p.isRequest()) {
          Appointment a = this.createAppointment(p);
          return a;
       } else {
          return null;
       }
    }
 
    public Appointment createAppointment(Patient var1) {
       return new Appointment(var1.getDoc(), var1, "Application has been passed to doctor");
    }
    public String getName() {
        return name;
    }
}

class Feedback {
    private String comments;
    private Prescription prescription;
    
    Feedback(String comments, Prescription prescription) {
        this.comments = comments;
        this.prescription = prescription;
    }
    public String getComments() {
        return comments;
    }
    public Prescription getPrescription() {
        return prescription;
    }
}

class Prescription {
    private String medication;
    private String dosage;
    private String schedule;
    
    Prescription(String medication, String dosage, String schedule) {
        this.medication = medication;
        this.dosage = dosage;
        this.schedule = schedule;
    }
    
    public void displayPrescription() {
        System.out.println("Medication: " + medication + ", Dosage: " + dosage + ", Schedule: " + schedule);
    }
}

class MedicalHistory {
    private ArrayList<Prescription> prescriptions = new ArrayList<>();
    
    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }
    
    public void displayHistory() {
        System.out.println("Medical History:");
        for (Prescription prescription : prescriptions) {
            prescription.displayPrescription();
        }
    }
}

interface Notifiable {

    void sendNotification(String recipient, String subject, String message);
}

class EmailNotification implements Notifiable{

    private final String senderEmail = "wasay3251@gmail.com";
    private final String senderPassword = "lzwp guyp ocps knwi";

    @Override
    public void sendNotification(String recipientEmail, String subject, String messageBody) {
        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail)
            );
            message.setSubject(subject);
            message.setText(messageBody);

            Transport.send(message);
            System.out.println("✅ Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

class SMSNotification implements Notifiable {
    @Override
    public void sendNotification(String recipient, String subject, String message) {
        System.out.println("\n=== SMS Notification ===");
        System.out.println("To: " + recipient);        
        System.out.println("Subject: " + subject);      
        System.out.println("Message: " + message);       
        System.out.println("======================\n");  
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\d{10}|\\d{11}");
    }

    public void sendNotification(String phoneNumber, String message) {
        if (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("Invalid phone number format: " + phoneNumber);
            return;
        }
        sendNotification(phoneNumber, "SMS Alert", message);
    }
}

class ReminderService {
    private final SMSNotification smsNotification;   
    private final Timer scheduler;                   

    public ReminderService() {
        this.smsNotification = new SMSNotification();
        this.scheduler = new Timer(true);            
    }

    public void scheduleAppointmentReminder(Appointment appointment) {
        Date appointmentDate = appointment.getDate_of_appointment();
        Date reminderTime = new Date(appointmentDate.getTime() - (24 * 60 * 60 * 1000));

        Patient patient = appointment.getPatient();
        if (patient != null) {
            @SuppressWarnings("unused")
            final String subject = "Appointment Reminder";
            final String message = String.format(
                "Reminder: You have an appointment with Dr. %s tomorrow at %s",
                appointment.getDoctor().getName(),
                appointmentDate.toString()
            );

            scheduler.schedule(new TimerTask() {
                @Override
                public void run() {
                    smsNotification.sendNotification(patient.getContact(), message);
                }
            }, reminderTime);
        }
    }

    public void scheduleMedicationReminder(Patient patient, String medication, String schedule) {
        final String subject = "Medication Reminder";
        final String message = String.format(
            "Reminder: Time to take your %s as per schedule: %s",
            medication,
            schedule
        );

        scheduler.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                smsNotification.sendNotification(patient.getContact(), subject, message);
            }
        }, 0, 24 * 60 * 60 * 1000);
    }

    public void sendImmediateReminder(Patient patient, String subject, String message) {
        smsNotification.sendNotification(patient.getContact(), subject, message);
    }

    public void cancelReminders(Patient patient) {
        scheduler.purge();
    }
}

class ChatServer{
    private Map<String, List<String>> chatHistory;

    public ChatServer() {
        this.chatHistory = new HashMap<>();
    }

    public void sendMessage(String sender, String receiver, String message) {
        String chatId = getChatId(sender, receiver);
        chatHistory.computeIfAbsent(chatId, k -> new ArrayList<>())
                  .add(String.format("[%s] %s: %s", 
                        new Date(), sender, message));
    }

    public List<String> getChatHistory(String user1, String user2) {
        return chatHistory.getOrDefault(getChatId(user1, user2), 
                                      new ArrayList<>());
    }
    
    private String getChatId(String user1, String user2) {
        return user1.compareTo(user2) < 0 
               ? user1 + "_" + user2 
               : user2 + "_" + user1;
    }
}

class ChatClient{
    private Users user;
    private ChatServer chatServer;
    
    public ChatClient(Users user, ChatServer chatServer) {
        this.user = user;
        this.chatServer = chatServer;
    }
    
    public void sendMessage(String receiverName, String message) {
        chatServer.sendMessage(user.getName(), receiverName, message);
    }
    
    public void displayChat(String otherUser) {
        System.out.println("\nChat history with " + otherUser + ":");
        for (String message : chatServer.getChatHistory(user.getName(), otherUser)) {
            System.out.println(message);
        }
    }
}

class VideoCall{
     private static final String MEET_BASE_URL = "https://meet.google.com/";
    
     public static void startVideoCall(String meetingId) {
         try {
             if (Desktop.isDesktopSupported()) {
                 Desktop desktop = Desktop.getDesktop();
                 URI meetingUri = new URI(MEET_BASE_URL + meetingId);
                 desktop.browse(meetingUri);
             } else {
                 System.out.println("Desktop browsing not supported. Meeting link: " 
                     + MEET_BASE_URL + meetingId);
             }
         } catch (Exception e) {
             System.out.println("Error starting video call: " + e.getMessage());
             System.out.println("Please manually open: " + MEET_BASE_URL + meetingId);
         }
     }
     
     public static String generateMeetingId() {
         String chars = "abcdefghijkmnopqrstuvwxyz";
         StringBuilder meetingId = new StringBuilder();
         Random random = new Random();
         
         // First part (3 characters)
         for (int i = 0; i < 3; i++) {
             meetingId.append(chars.charAt(random.nextInt(chars.length())));
         }
         meetingId.append("-");
         
         // Second part (4 characters)
         for (int i = 0; i < 4; i++) {
             meetingId.append(chars.charAt(random.nextInt(chars.length())));
         }
         meetingId.append("-");
         
         // Third part (3 characters)
         for (int i = 0; i < 3; i++) {
             meetingId.append(chars.charAt(random.nextInt(chars.length())));
         }
         
         return meetingId.toString();
     }
}

class EmergencyAlert{
    private String alertMessage;
    private boolean isCritical;
    private Doctor d;
    private EmailNotification en;
    private Patient p;

    public EmergencyAlert(Patient p, Doctor d, EmailNotification en){
        this.p = p;
        this.d = d;
        this.en = en;
    }
    public String getAlertMessge() {
        return alertMessage;
    }
    public Doctor getD() {
        return d;
    }
    public EmailNotification getEn() {
        return en;
    }
    public Patient getP() {
        return p;
    }
    public boolean isCritical() {
        return isCritical;
    }

    private final int maxHeartRate = 100;
    private final int minHeartRate = 60;
    private final int maxTemp = 99;
    private final int minTemp = 97;
    private final int maxBP = 120;
    private final int minBP = 80;
    
    public void checkVitals(int heart_rate,int temperature, int blood_pressure){
        if(heart_rate>maxHeartRate || heart_rate<minHeartRate){
            triggerAlert("Critical HeartRate: " + heart_rate);
        }
        if(temperature>maxTemp || temperature<minTemp){
            triggerAlert("Critical Temperature: " + temperature);
        }
        if(blood_pressure>maxBP || blood_pressure<minBP){
            triggerAlert("Critical Blood Pressure: " + blood_pressure);
        }
        else{
            System.out.println("Patient is free from any emergency");
        }
    }

    public void triggerAlert(String message){
        if(message == null || message.isBlank()) {
            this.alertMessage = "Critical health emergency detected";
        } else {
            this.alertMessage = message;
        }
        sendEmailAlert(d, en);
    }

    public void sendEmailAlert(Doctor d, EmailNotification en){
        en.sendNotification(d.getEmail(), "Emergency", getAlertMessge());
    }
}

class NotificationService{

    public void sendEmailAlert(Doctor d, EmergencyAlert e, EmailNotification en){
        en.sendNotification(d.getEmail(), "Emergency", e.getAlertMessge());
    }

    public void sendSMSAlert(Doctor d,EmergencyAlert e, SMSNotification sms){
        sms.sendNotification(d.getContact(), "Emergency", e.getAlertMessge() );
    }

}

class PanicButton {
    private final Patient patient;
    private final Doctor doctor;
    private final EmailNotification notifier;

    public PanicButton(Doctor doctor, Patient patient, EmailNotification notifier) {
        this.doctor = doctor;
        this.patient = patient;
        this.notifier = notifier;
    }

    public void press() {
        String alertMessage = "Emergency! Patient " + patient.getName() 
                           + " activated panic button!";
        notifier.sendNotification(
            doctor.getEmail(), 
            "PANIC BUTTON ACTIVATED", 
            alertMessage
        );
    }
}

public class assignment {
    public static void main(String[] args) {
        System.out.println("=== RHMS System Test ===");
        
        // Create core objects
        Doctor doc = new Doctor(1, "Dr. Wasay", "wasay3251@gmail.com", "9876543210");
        Patient patient = new Patient(doc, 1, "Habib Hamid", "wasay3251@gmail.com", "9123456789", 28);
        EmailNotification email = new EmailNotification();
        
        testAppointmentSystem(doc, patient);
        testVitalMonitoring(patient);
        testEmergencySystem(patient, doc, email);
        testChatFunctionality(doc, patient);
        testVideoCallSystem();
    }

    private static void testAppointmentSystem(Doctor doc, Patient patient) {
        System.out.println("\n=== Appointment System Test ===");
        
        // Request appointment
        patient.requestAppointment();
        System.out.println("Appointment requested: " + patient.isRequest());
        
        // Create appointment
        AppointmentManager manager = new AppointmentManager("Main Scheduler");
        Appointment appt = manager.createAppointment(patient);
        System.out.println("Appointment created: " + appt.status);
        
        // Doctor actions
        doc.approveAppointment(appt);
        System.out.println("After approval: " + appt.status);
        doc.rejectAppointment(appt);
        System.out.println("After rejection: " + appt.status);
    }

    private static void testVitalMonitoring(Patient patient) {
        System.out.println("\n=== Vital Monitoring Test ===");
        
        VitalsDatabase db = new VitalsDatabase();
        VitalSign vs = new VitalSign("88", "97", "110/70", "98.2");
        db.addVital(vs, patient);
        
        VitalSign retrieved = db.retrieveVital(patient);
        System.out.println("Stored vitals: " + retrieved);
    }

    private static void testEmergencySystem(Patient patient, Doctor doc, EmailNotification email) {
        System.out.println("\n=== Emergency System Test ===");
        
        EmergencyAlert alert = new EmergencyAlert(patient, doc, email);
        System.out.println("Testing normal vitals:");
        alert.checkVitals(75, 98, 110);
        
        System.out.println("\nTesting critical vitals:");
        alert.checkVitals(150, 101, 130);
        System.out.println("Alert message: " + alert.getAlertMessge());
        
        PanicButton button = new PanicButton(doc, patient, email);
        System.out.println("\nTesting panic button:");
        button.press();
    }

    private static void testChatFunctionality(Doctor doc, Patient patient) {
        System.out.println("\n=== Chat System Test ===");
        
        ChatServer server = new ChatServer();
        ChatClient docClient = new ChatClient(doc, server);
        ChatClient patientClient = new ChatClient(patient, server);
        
        patientClient.sendMessage("Dr. Wasay", "Having headache");
        docClient.sendMessage("Habib Hamid", "Take paracetamol");
        
        System.out.println("Chat history:");
        patientClient.displayChat("Dr. Sam");
    }

    private static void testVideoCallSystem() {
        System.out.println("\n=== Video Call Test ===");
        String meetingId = VideoCall.generateMeetingId();
        System.out.println("Generated meeting ID: " + meetingId);
        System.out.println("(Simulation) Starting video call...");
        VideoCall.startVideoCall(meetingId);
    }
}
