create table admin
(
    admin_id int auto_increment
        primary key,
    Fname    varchar(25) not null,
    Lname    varchar(25) not null,
    email    varchar(25) not null,
    password varchar(35) not null,
    constraint password
        check (octet_length(`password`) > 6 and octet_length(`password`) <= 35)
);

create table department
(
    departmentid   int auto_increment
        primary key,
    departmentname varchar(25) null
);

create table diagnosis
(
    Diagnosis_ID      int auto_increment
        primary key,
    Diagnosis_Status  varchar(50) not null,
    Diagnosis_Name    varchar(50) not null,
    Follow_Up_Methods varchar(50) not null
);

create table doctor
(
    doctor_id    int auto_increment
        primary key,
    Fname        varchar(25) not null,
    Lname        varchar(25) not null,
    age          int         null,
    sex          varchar(30) not null,
    Phoneno      varchar(25) not null,
    experience   varchar(10) not null,
    position     text        not null,
    departmentid int         null,
    email        varchar(25) not null,
    DOB          date        null,
    constraint doctor_ibfk_1
        foreign key (departmentid) references department (departmentid)
            on update cascade on delete set null
);

create index departmentid
    on doctor (departmentid);

create table hospital
(
    hospitalname varchar(30) null,
    location     varchar(30) null
);

create table patient
(
    patient_id int(15) auto_increment
        primary key,
    Fname      varchar(30) not null,
    Lname      varchar(30) not null,
    email      varchar(30) not null,
    password   varchar(30) not null,
    address    varchar(70) not null,
    phoneno    varchar(30) not null,
    sex        varchar(30) not null,
    DOB        date        not null,
    age        int         not null,
    bloodgroup varchar(23) not null
);

create table appointment
(
    appointmentid   int(10) auto_increment
        primary key,
    doctorid        int     null,
    patientid       int(15) null,
    appointmentdate date    null,
    constraint appointment_ibfk_1
        foreign key (doctorid) references doctor (doctor_id),
    constraint appointment_ibfk_2
        foreign key (patientid) references patient (patient_id)
);

create index doctorid
    on appointment (doctorid);

create index patientid
    on appointment (patientid);

create table bloodbank
(
    Bloodserialno int auto_increment
        primary key,
    BloodType     varchar(20) not null,
    DonationDate  date        not null,
    PatientID     int         null,
    constraint BloodBank_patient_patient_id_fk
        foreign key (PatientID) references patient (patient_id)
            on update cascade on delete set null
);

create table deathreport
(
    reportId   int auto_increment
        primary key,
    patientid  int         null,
    Deathdate  date        not null,
    DeathPlace varchar(40) not null,
    DeathCause varchar(20) not null,
    constraint DeathReport_patient__fk
        foreign key (patientid) references patient (patient_id)
            on update cascade on delete cascade
);

create table qualification
(
    qualification_id  int auto_increment
        primary key,
    qualificationname varchar(40) not null,
    institutename     varchar(40) not null,
    graduationyear    date        not null,
    doctorid          int         not null,
    constraint qualification_ibfk_1
        foreign key (doctorid) references doctor (doctor_id)
            on update cascade on delete cascade
);

create index doctorid
    on qualification (doctorid);

create table specialization
(
    specialization_id int auto_increment
        primary key,
    spec_name         varchar(40) not null
);

create table doctor_specialization
(
    doctor_id         int not null,
    specialization_id int not null,
    primary key (doctor_id, specialization_id),
    constraint doctor_specialization_ibfk_1
        foreign key (specialization_id) references specialization (specialization_id)
            on update cascade on delete cascade
);

create index specialization_id
    on doctor_specialization (specialization_id);

create table ward
(
    wardno          int         not null
        primary key,
    wardname        varchar(25) not null,
    floorno         int         not null,
    ExtensionNumber varchar(20) not null,
    noofbeds        int(10)     null,
    constraint noofbeds
        check (`noofbeds` < 30),
    constraint wardno
        check (`wardno` > 0)
);

create table admitted_patients
(
    Admitted_ID        int      not null
        primary key,
    PatientID          int(15)  not null,
    Ward_No            int      null,
    Admitted_Date_Time datetime not null,
    diagnosis_record   int      null,
    constraint diagnosis_record
        unique (diagnosis_record),
    constraint admitted_patients_ibfk_1
        foreign key (PatientID) references patient (patient_id)
            on update cascade on delete cascade,
    constraint admitted_patients_ibfk_2
        foreign key (Ward_No) references ward (wardno)
            on update cascade on delete set null
);

create index PatientID
    on admitted_patients (PatientID);

create index Ward_No
    on admitted_patients (Ward_No);

create table `diagnosis record`
(
    diagnosis_record int not null,
    Diagnosis_id     int not null,
    primary key (diagnosis_record, Diagnosis_id),
    constraint `diagnosis record_ibfk_1`
        foreign key (Diagnosis_id) references diagnosis (Diagnosis_ID)
            on update cascade on delete cascade,
    constraint `diagnosis record_ibfk_2`
        foreign key (diagnosis_record) references admitted_patients (diagnosis_record)
            on update cascade on delete cascade
);

create index Diagnosis_id
    on `diagnosis record` (Diagnosis_id);

create table room
(
    roomno     int auto_increment
        primary key,
    WardNo     int         not null,
    Status     varchar(45) not null,
    isOccupied tinyint(1)  null,
    constraint room_ibfk_1
        foreign key (WardNo) references ward (wardno)
);

create index WardNo
    on room (WardNo);


