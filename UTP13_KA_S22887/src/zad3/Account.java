package zad3;

import java.beans.*;


public class Account{
    private double balance;
    private int number;
    private static int count = 1;

    public Account (){
        this.balance = 0;
        this.number = count++;
    }

    public Account(double balance) {
        this.balance = balance;
        this.number = count++;
    }

    public int getNumber() {
        return number;
    }

    public void deposit(double sum) throws PropertyVetoException {
        double sumAfter = this.balance + sum;
        setBalance(sumAfter);
    }

    public void withdraw(double sum) throws PropertyVetoException {
        double sumAfter = this.balance - sum;
        setBalance(sumAfter);
    }

    public void transfer(Account account, double sum) throws PropertyVetoException {
        setBalance(this.balance - sum);
        account.setBalance(account.getBalance() + sum);
    }

    public double getBalance() {
        return balance;
    }

    private VetoableChangeSupport vetos = new VetoableChangeSupport(this);

    public synchronized void addVetoableChangeListener(VetoableChangeListener l) {
        vetos.addVetoableChangeListener(l);
    }

    public synchronized void removeVetoableChangeListener(VetoableChangeListener l) {
        vetos.removeVetoableChangeListener(l);
    }

    private PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);

    public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChange.addPropertyChangeListener(l);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChange.removePropertyChangeListener(l);
    }

    public synchronized void setBalance(double sum) throws PropertyVetoException {
        double oldValue = this.balance;
        String numberCount = String.valueOf(getNumber());

        vetos.fireVetoableChange(numberCount, Integer.valueOf((int) oldValue), Integer.valueOf((int) (sum)));

        this.balance = sum;

        propertyChange.firePropertyChange(numberCount, oldValue, sum);
    }

    @Override
    public String toString() {
        return "Acc " + this.getNumber() + ": " + this.getBalance();
    }
}
