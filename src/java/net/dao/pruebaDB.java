package net.dao;

import net.models.Usuario;

public class pruebaDB {
    public static void main(String[] args){
        dbConnection conn = new dbConnection();
        
        UsuaroDao usrDao = new UsuaroDao(conn);
        Usuario user = usrDao.login("angeles", "12345");
        System.out.println("USER ==> " + user);
        
    }
}
