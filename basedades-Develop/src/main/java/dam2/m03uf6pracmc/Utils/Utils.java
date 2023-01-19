/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam2.m03uf6pracmc.Utils;


import dam2.m03uf6pracmc.Logica.LogicaAppConfig;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;

public class Utils {
        LogicaAppConfig logicaapp;
       // public APPConfig appcfg=logicaapp.carregarDades();
    public static Boolean validarDNICliente(String idCard) {
        String m = "";
        Pattern mailPattern = Pattern.compile("^[0-9]{8,8}[A-Za-z]$");
        Matcher matcher = mailPattern.matcher(idCard);
        if (!matcher.matches()) {
            m = "El DNI no es valido";

        }
        return matcher.matches();

    }
    public static boolean validaEmailCLiente(String email) {
        Boolean ret = false;
        Pattern regles = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");

        if (regles.matcher(email).find()) {
            ret = true;
        }

        return ret;
    }
     public static Boolean validarNombreCliente(String nombre) {
        String n=""; 
        Pattern mailPattern = Pattern.compile("^[A-z][a-zA-Z]+$");
        Matcher matcher = mailPattern.matcher(nombre);
        if (!matcher.matches()) {
            n = "El nombre no es valido\n";

        }
        return matcher.matches();

    }
     //metodo para validar un telefono con regex
      public static Boolean validarTelefon(String telef) {
          String t="";
        Pattern phonePattern = Pattern.compile("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$");
        Matcher matcher = phonePattern.matcher(telef);
        if (!matcher.matches()) {
            t= "Telefono no valido\n";

        }
        return matcher.matches();

    }
       public static Boolean validarlimiteDinero(String creditoLim) {
           String c="";
        double value = Double.parseDouble(creditoLim);
        if (value >= 0) {
            return true;
        }
        c= "El limite debe ser > = 0\n";
        return false;

    }
//        public static Boolean validarFechaNacimiento(LocalDate fecha) {
//        String c="";
//        Period period = Period.between(fecha, LocalDate.now());
//        if (period.getYears() >= appcfg.getEdad()) {
//            return true;
//        } else {
//            c= "La Fecha introducida no es valida\n";
//
//        }
//        return false;
//
//    }
    public static void mostraMissatge(int tipus, String txt) {

        Alert alert;

        switch (tipus) {
            case 0: {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("INFORMACIO");
            }
            break;
            case 1: {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
            }
            break;
            default:
                alert = new Alert(Alert.AlertType.INFORMATION);
        }

        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setContentText(txt);
        alert.showAndWait();
    }
}
