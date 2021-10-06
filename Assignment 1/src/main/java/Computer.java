/**
 * Assignment 0.
 *
 * @author Charit Patel
 * Written by: Charit Patel, id: 40160658
 */
public class Computer {

    public static int count = 0;
    private String brand;
    private String model;
    private long SN;
    private double price;

    /**
     * Default constructor, it initializes variables with predefined values.
     */
    public Computer() {
        brand = "HP";
        model = "Pavilion";
        SN = 123456789;
        price = 50000;
        count++;
    }

    /**
     * Parameterized constructor, it initializes variables with the values provided by the user while creating the object.
     *
     * @param brand brand name
     * @param model model name
     * @param SN    Serial number
     * @param price price
     */
    public Computer(String brand, String model, long SN, double price) {
        this.brand = brand;
        this.model = model;
        this.SN = SN;
        this.price = price;
        count++;
    }

    /**
     * Copy constructor. It accepts computer class object and stores it variables into the current object variables.
     *
     * @param com Computer class object.
     */
    public Computer(Computer com) {
        this.brand = com.brand;
        this.model = com.model;
        this.SN = com.SN;
        this.price = com.price;
        count++;
    }

    /**
     * Returns number of created objects till now.
     *
     * @return number of objects.
     */
    public static int findNumberOfCreatedComputers() {
        return count;
    }

    /**
     * Sets number of objects.
     *
     * @param count number of objects.
     */
    public static void setCount(int count) {
        Computer.count = count;
    }

    /**
     * Returns brand name.
     *
     * @return brand name
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets brand name.
     *
     * @param brand brand name
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Returns model name
     *
     * @return model name
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets model name
     *
     * @param model model name
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Returns serial number
     *
     * @return serial number
     */
    public long getSN() {
        return SN;
    }

    /**
     * Sets serial number
     *
     * @param SN serial number
     */
    public void setSN(long SN) {
        this.SN = SN;
    }

    /**
     * Returns price
     *
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price
     *
     * @param price price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Overriding toString() method.
     * Displays all the information of the object through the utilization of System.out.print() method.
     *
     * @return information of the object.
     */
    public String toString() {
        return "Brand: " + brand + "\n" + "Model: " + model + "\n" + "SN: " + SN + "\n" + "Price: " + price;
    }

    /**
     * Checks two objects are equal or not.
     *
     * @param obj Object
     * @return true if attribute values are same, otherwise false
     */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        Computer computer = (Computer) obj;
        return (computer.brand == this.brand && computer.model == this.model && computer.price == this.price);
    }
}
