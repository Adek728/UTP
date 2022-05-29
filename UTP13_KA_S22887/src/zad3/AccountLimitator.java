package zad3;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;


public class AccountLimitator implements VetoableChangeListener {
    private int limit;

    public AccountLimitator(int limit) {
        this.limit = limit;
    }
    public void vetoableChange(PropertyChangeEvent e) throws PropertyVetoException {
        Integer newVal = (Integer) e.getNewValue();
        double val = (double) newVal;
        if (newVal < limit) {
            throw new PropertyVetoException(e.getPropertyName() + ": Unacceptable value change: " + val, e);
        }
    }
}
