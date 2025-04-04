import javax.print.Doc;
import java.util.ArrayList;
import java.util.Date;

class Users{
       protected int id;
       protected String name;
       protected String email;
       protected int contact;
       Users(){};
       Users(int id,String name,String email,int contact){
           this.id=id;
           this.name=name;
           this.email=email;
           this.contact=contact;
       }
}

class Patient extends Users{
     private int age;
     private Doctor doc;
     private boolean request;
     private String feedback;
     private MedicalHistory medicalHistory;
     
     Patient(Doctor doc,int id,String name,String email,int contact,int age){
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
    public int getContact(){
         return contact;
    }
}

class Doctor extends Users{

     Doctor(int id,String name,String email,int contact){
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
}

class Administrator extends Users{
    Administrator(int id,String name,String email,int contact){
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

class Feedback {
    private String comments;
    private Prescription prescription;
    
    Feedback(String comments, Prescription prescription) {
        this.comments = comments;
        this.prescription = prescription;
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

public class assignment {
    public static void main(String[] args) {
        Administrator a1 = new Administrator(1, "Wasay", "adminwasay", 3258912);
        Doctor d1 = new Doctor(1, "John", "docjohn", 1230);
        Patient p1 = new Patient(d1, 1, "David", "patientzain", 2233, 19);
        VitalSign v1 = new VitalSign("108", "112", "180", "98");
        VitalsDatabase vb = new VitalsDatabase();
        vb.addVital(v1, p1);
        a1.addDoctor(d1);
        a1.addPatient(p1);
        p1.requestAppointment();
        Appointment ap1 = new Appointment(d1, p1, "Pending");
        d1.approveAppointment(ap1);
        d1.giveFeedback(p1, "Patient's rehab is going well. Please re-visit in 14-21 days.");
        d1.prescribeMedication(p1, "Paracetamol", "500mg", "Twice a day");
        d1.prescribeMedication(p1, "Ibuprofen", "200mg", "After meals");
        System.out.println("--- Patient & Vitals details ---\n");
        d1.viewPatientData(p1, vb);

        System.out.println("\n--- Medical History and Prescriptions ---");
        p1.viewMedicalHistory();
    }
}
