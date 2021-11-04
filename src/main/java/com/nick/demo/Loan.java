package com.nick.demo;



import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;


/**
 * This class defines a loan. It is associated with an applicant, has an amount, and a status.
 */
public class Loan {

    private String uuid, name, status;
    private long amount;
    private long timestamp;
    private String ip;

    public Loan() {
    }

    public Loan(String uuid, String name, long amount) {
        this.uuid = uuid;
        this.name = name;
        this.amount = amount;
        this.timestamp=System.currentTimeMillis();

        try {
            this.ip= InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.setStatus(Statuses.PENDING.name());
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setStatus(String status) {
        if (status.equals(Statuses.APPROVED.name())
                || status.equals(Statuses.DECLINED.name())
                || status.equals(Statuses.PENDING.name())
                || status.equals(Statuses.REJECTED.name())) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Cannot set the LoanApplication's status to " + status);
        }
    }

    public long getAmount() {
        return amount;
    }


    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan that = (Loan) o;
        return amount == that.amount &&
                uuid.equals(that.uuid) &&
                name.equals(that.name) &&
                timestamp==that.timestamp &&
                Objects.equals(ip, that.ip) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, name, status, amount,timestamp,ip);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", ip='" + ip + '\'' +
                ", amount=" + amount +
                '}';
    }
}
