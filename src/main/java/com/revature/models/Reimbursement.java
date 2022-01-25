package com.revature.models;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

public class Reimbursement {

    private enum ReimbursementStatus {
        PENDING, APPROVED, DENIED
    }

    private enum ReimbursementType {
        LODGING, TRAVEL, FOOD, OTHER
    }

    private static final ReimbursementStatus[] reimbursementStatusValues = ReimbursementStatus.values();
    private static final ReimbursementType[] reimbursementTypeValues = ReimbursementType.values();

    private int id;
    private double amount;
    private Timestamp dateSubmitted;
    private Timestamp dateResolved;
    private String description;
    private byte[] receipt;
    private int authorId;
    private int resolverId;
    private int statusId;
    private ReimbursementStatus status;
    private int typeId;
    private ReimbursementType type;

    public Reimbursement() {  }

    public Reimbursement(int id, double amount, Timestamp dateSubmitted, Timestamp dateResolved,
                         String description, int authorId, int resolverId, int statusId, int typeId) {
        this.id = id;
        this.amount = amount;
        this.dateSubmitted = dateSubmitted;
        this.dateResolved = dateResolved;
        this.description = description;
        this.authorId = authorId;
        this.resolverId = resolverId;
        this.statusId = statusId;
        this.typeId = typeId;

        this.status = reimbursementStatusValues[statusId];
        this.type = reimbursementTypeValues[typeId];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Timestamp dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public Timestamp getDateResolved() {
        return dateResolved;
    }

    public void setDateResolved(Timestamp dateResolved) {
        this.dateResolved = dateResolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getReceipt() {
        return receipt;
    }

    public void setReceipt(byte[] receipt) {
        this.receipt = receipt;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getResolverId() {
        return resolverId;
    }

    public void setResolverId(int resolverId) {
        this.resolverId = resolverId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
        this.status = reimbursementStatusValues[statusId];
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
        this.type = reimbursementTypeValues[typeId];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return id == that.id && Double.compare(that.amount, amount) == 0 && authorId == that.authorId && resolverId == that.resolverId && statusId == that.statusId && typeId == that.typeId && Objects.equals(dateSubmitted, that.dateSubmitted) && Objects.equals(dateResolved, that.dateResolved) && Objects.equals(description, that.description) && Arrays.equals(receipt, that.receipt);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, amount, dateSubmitted, dateResolved, description, authorId, resolverId, statusId, typeId);
        result = 31 * result + Arrays.hashCode(receipt);
        return result;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", amount=" + amount +
                ", dateSubmitted='" + dateSubmitted + '\'' +
                ", dateResolved='" + dateResolved + '\'' +
                ", description='" + description + '\'' +
                ", receipt=" + Arrays.toString(receipt) +
                ", authorId=" + authorId +
                ", resolverId=" + resolverId +
                ", statusId=" + statusId +
                ", typeId=" + typeId +
                '}';
    }
}
