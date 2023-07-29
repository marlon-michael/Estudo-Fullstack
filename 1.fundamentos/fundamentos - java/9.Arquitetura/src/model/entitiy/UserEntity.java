package model.entitiy;


// uma entidade faz o mapeamento completo da tabela no banco de dados
// e serve para manipulação dos dados por meio deste modelo

// entidade de mapeamento de uma tabela com colunas: id, creditCard, username, password
public class UserEntity {
    private Long id;
    private String creditCard;
    private String username;
    private String password;

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
