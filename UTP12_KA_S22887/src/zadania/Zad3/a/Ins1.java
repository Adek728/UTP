package zadania.Zad3.a;

import java.sql.*;

public class Ins1 {

    static public void main(String[] args) {
        new Ins1();
    }

    Statement stmt;
    String url = "jdbc:derby:C:/DerbyDbs/ksidb";

    Ins1()  {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
            stmt = con.createStatement();
        } catch (Exception exc)  {
            System.out.println(exc);
            System.exit(1);
        }
        // nazwy wydawców do wpisywania do tabeli
        String[] wyd =  { "PWN", "PWE", "Czytelnik", "Amber", "HELION", "MIKOM" };

        // pierwszy numer wydawcy do wpisywania do tabeli: PWN ma numer 15, PWE ma 16, ...
        int beginKey = 15;

        String[] ins = new String[6]; // ? ... tablica instrukcji SQL do wpisywania rekordów do tabeli: INSERT ...
        ins[0] = "INSERT INTO WYDAWCA VALUES (" + beginKey++ + ", '" + wyd[0] + "')";
        ins[1] = "INSERT INTO WYDAWCA VALUES (" + beginKey++ + ", '" + wyd[1] + "')";
        ins[2] = "INSERT INTO WYDAWCA VALUES (" + beginKey++ + ", '" + wyd[2] + "')";
        ins[3] = "INSERT INTO WYDAWCA VALUES (" + beginKey++ + ", '" + wyd[3] + "')";
        ins[4] = "INSERT INTO WYDAWCA VALUES (" + beginKey++ + ", '" + wyd[4] + "')";
        ins[5] = "INSERT INTO WYDAWCA VALUES (" + beginKey++ + ", '" + wyd[5] + "')";
        int insCount = 0;   // ile rekordów wpisano
        try {
            for (int i = 0; i < ins.length; i++) {
                insCount++;
                try {
                    stmt.executeUpdate(ins[i]);// wpisywanie rekordów
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            // ...
        } finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
