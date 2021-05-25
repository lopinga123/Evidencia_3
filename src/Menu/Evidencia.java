package Menu;

import java.util.InputMismatchException;
import java.util.Scanner;
import DB.Database;


public class Evidencia {

    public static void main(String[] args) {

        Database db = new Database();
        Scanner s = new Scanner(System.in);
        db.connect();
        boolean end = false;
        boolean programEnd = false;
        boolean sesionIniciada;
        System.out.println("Agenda de citas o doctores");
        System.out.print("Ingrese su usuario: ");
        String user = s.next();
        System.out.print("Ingrese su contraseña: ");
        String password = s.next();
        Sesion sesion = new Sesion(user, password);
        System.out.println("\n");
        sesionIniciada = sesion.iniciarSesion(db);
        System.out.println("\n");
        if (sesionIniciada == true) {
            while (programEnd == false) {
                switch (Menu()) {
                    case 1:
                        end = false;
                        System.out.println("\nRegistro de pacientes:");
                        while (end == false) {
                            end = agregarPaciente(db);
                        }
                        break;
                    case 2:
                        System.out.println("\nLista de pacientes registados:");
                        db.mostrarPacientes();
                        break;
                    case 3:
                        end = false;
                        System.out.println("\nRegistro de doctores:");
                        while (end == false) {
                            end = agregarDoctor(db);
                        }
                        break;
                    case 4:
                        System.out.println("\nLista de doctores registados:");
                        db.mostrarDoctores();
                        break;
                    case 5:
                        end = false;
                        System.out.println("\nAgenda de citas:");
                        while (end == false) {
                            end = agregarCita(db);
                        }
                        break;
                    case 6:
                        System.out.println("\nLista de citas agendadas:");
                        db.mostrarCitas();
                        break;
                    case 7:
                        end = false;
                        System.out.println("\nRelacionar citas:");
                        while (end == false) {
                            end = relacionarCitas(db);
                        }
                        break;
                    case 8:
                        System.out.println("\nLista de citas relacionadas:");
                        db.mostrarCitasRelacionadas();
                        break;
                    case 0:
                        programEnd = true;
                        db.close();
                        System.out.println("\nGracias por tu vista");
                        break;
                    default:
                        System.out.println("Selecciona una opción válida");
                        break;
                }
            }
        }
    }

    public static int Menu(){
        Scanner s = new Scanner(System.in);
        int respuesta=0;
        System.out.printf(
                "Menú"+
                        "\n1. Dar de alta paciente\n"+
                        "2. Mostrar todos los pacientes\n"+
                        "3. Dar de alta doctor\n"+
                        "4. Mostrar todos los doctores\n"+
                        "5. Agendar cita\n"+
                        "6. Mostrar citas\n"+
                        "7. Relacionar citas\n"+
                        "8. Mostrar citas relacionadas\n"+
                        "0. Salir\n"+
                        "¿Qué deseeas hacer? \n");
        respuesta = s.nextInt();
        System.out.println("\n");
        return respuesta;
    }

    public static boolean agregarPaciente(Database db){
        Scanner s = new Scanner(System.in);
        try{
            System.out.print("Ingresa el ID del paciente: ");
            int idPaciente = s.nextInt();
            System.out.print("Ingresa el nombre del paciente: ");
            s.nextLine(); //Salto de línea
            String nombrePaciente = s.nextLine();
            System.out.print("(Opcional) Ingresa el problema del paciente: ");
            String descProb = s.nextLine();
            if(descProb.equals("")){
                descProb = "No especificado";
            }
            Paciente paciente = new Paciente(nombrePaciente,idPaciente,descProb);
            return db.agregarPaciente(paciente);
        } catch (Exception ex){
            System.err.println("Ingrese bien los datos");
            return false;
        }
    }

    public static boolean agregarDoctor(Database db){
        Scanner s = new Scanner(System.in);
        try {
            System.out.print("Ingresa el ID del doctor: ");
            int idDoctor = s.nextInt();
            System.out.print("Ingresa el nombre del doctor: ");
            s.nextLine(); //Salto de Línea
            String nombreDoctor = s.nextLine();
            System.out.print("Ingresa la especialidad del doctor: ");
            String especialidad = s.nextLine();
            Doctor doctor = new Doctor(nombreDoctor,idDoctor,especialidad);
            return db.agregarDoctor(doctor);
        } catch (Exception ex){
            System.err.println("Ingrese bien los datos");
            return false;
        }

    }

    public static boolean agregarCita(Database db){
        Scanner s = new Scanner(System.in);
        try{
            System.out.print("Ingresa el ID de la cita: ");
            int idCita = s.nextInt();
            System.out.print("Ingresa la fecha de la cita: ");
            s.nextLine(); //Salto de Línea
            String fecha = s.nextLine();
            System.out.print("Ingresa la hora de la cita: ");
            String hora = s.nextLine();
            Cita cita = new Cita(idCita,fecha,hora);
            return db.agregarCita(cita);
        } catch(Exception ex){
            System.err.println("Ingrese bien los datos");
            return false;
        }

    }

    public static boolean relacionarCitas(Database db){
        Scanner s = new Scanner(System.in);
        try{
            System.out.print("Ingresa el ID de la cita: ");
            int idCita = s.nextInt();
            System.out.print("Ingresa el ID del doctor: ");
            int idDoctor = s.nextInt();
            System.out.print("Ingresa el ID del paciente: ");
            int idPaciente = s.nextInt();
            return db.relacionarCitas(idCita,idDoctor, idPaciente);
        } catch(Exception ex){
            System.err.println("Ingrese bien los datos");
            return false;
        }
    }
}