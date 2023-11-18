# Criptografia


### principais algoritimos criptográficos
- SHA-2: SHA-256
- SHA-3: SHA3-256

### algoritimos criptográficos considerados obsoletos
- SHA-1
- MD5

### como utilizar SHA-256 em Java
- importações
```java
import java.security.MessageDigest;
```

- imprementação
```java
String password = "password"; // senha sem criptografia

// estrutura para tratamento de erro
try{
    // instancia algoritimo 
    MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
    // senha criptografada em formato de bytes
    byte bytePass = algorithm.digest(password.getBytes("UTF-8"));
    // senha criptografada em hexadecimal
    StringBuilder hexPass = new StringBuilder();
    for (byte b: bytePass){
        hexPass.append(String.format("%02X", 0xFF & b))
    }
    String pass = hexPass.toString()
}
catch(Exceptions error){
    System.out.println("Não foi possível criptografar a string");
}
```