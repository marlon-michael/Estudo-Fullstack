import java.security.MessageDigest;


public class Criptografia{
    public static void main(String []args){
        System.out.println("\n-------- RUNNING --------\n");
        String password = "password";
    
        try{
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte hashPass [] = algorithm.digest(password.getBytes("UTF-8"));
            StringBuilder hexPass = new StringBuilder();
            for (byte b: hashPass){
                hexPass.append(String.format("%02X", 0xFF & b));
            }
            String pass = hexPass.toString();
            System.out.println(pass);
        }
        catch(Exception error){
            System.out.println("Não foi possível criptografar a string");
        }
        System.out.println("\n-------- ENDING --------\n");
    }
}