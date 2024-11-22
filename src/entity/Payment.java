package entity;
import java.util.Date;

public class Payment {
    private int paymentId;
    private Date paymentDate;
    private double paymentAmount;
    private Client client;
    
    
    Payment(int paymentId,Date paymentDate,double paymentAmount,Client client){
    	this.paymentId=paymentId;
    	this.paymentDate=paymentDate;
    	this.paymentAmount=paymentAmount;
    	this.client=client;
    	}
    
    public int getPaymentID() {
    	return paymentId;
    }
    public void setPaymentID(int paymentId) {
    	this.paymentId=paymentId;
    }
    public	Date getPaymentDate() {
    	return paymentDate;
    }
    public void setPaymentDate(Date paymentDate) {
    	this.paymentDate=paymentDate;
    }
    public double getpaymentAmount() {
    	return paymentAmount;
    }
    public void getpaymentAmount(double paymentAmount) {
    	this.paymentAmount=paymentAmount;
    } 
    public Client getClient() {
    	return client;
    	
    }
    public void setClient(Client client) {
    	this.client=client;
    }
    
    @Override
    
    public String toString() {
    	return "Payment [paymentId=" + paymentId + ", paymentDate=" + paymentDate + ", paymentAmount=" + paymentAmount +
                ", client=" + client + "]";
    }
}
