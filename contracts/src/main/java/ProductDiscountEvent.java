/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class ProductDiscountEvent extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 6622282745016191138L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"ProductDiscountEvent\",\"fields\":[{\"name\":\"productId\",\"type\":\"long\"},{\"name\":\"percentage\",\"type\":\"double\"},{\"name\":\"startDate\",\"type\":\"long\"},{\"name\":\"hours\",\"type\":\"int\"},{\"name\":\"discountCode\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<ProductDiscountEvent> ENCODER =
      new BinaryMessageEncoder<ProductDiscountEvent>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<ProductDiscountEvent> DECODER =
      new BinaryMessageDecoder<ProductDiscountEvent>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<ProductDiscountEvent> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<ProductDiscountEvent> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<ProductDiscountEvent> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<ProductDiscountEvent>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this ProductDiscountEvent to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a ProductDiscountEvent from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a ProductDiscountEvent instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static ProductDiscountEvent fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private long productId;
   private double percentage;
   private long startDate;
   private int hours;
   private java.lang.CharSequence discountCode;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public ProductDiscountEvent() {}

  /**
   * All-args constructor.
   * @param productId The new value for productId
   * @param percentage The new value for percentage
   * @param startDate The new value for startDate
   * @param hours The new value for hours
   * @param discountCode The new value for discountCode
   */
  public ProductDiscountEvent(java.lang.Long productId, java.lang.Double percentage, java.lang.Long startDate, java.lang.Integer hours, java.lang.CharSequence discountCode) {
    this.productId = productId;
    this.percentage = percentage;
    this.startDate = startDate;
    this.hours = hours;
    this.discountCode = discountCode;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return productId;
    case 1: return percentage;
    case 2: return startDate;
    case 3: return hours;
    case 4: return discountCode;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: productId = (java.lang.Long)value$; break;
    case 1: percentage = (java.lang.Double)value$; break;
    case 2: startDate = (java.lang.Long)value$; break;
    case 3: hours = (java.lang.Integer)value$; break;
    case 4: discountCode = (java.lang.CharSequence)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'productId' field.
   * @return The value of the 'productId' field.
   */
  public long getProductId() {
    return productId;
  }


  /**
   * Sets the value of the 'productId' field.
   * @param value the value to set.
   */
  public void setProductId(long value) {
    this.productId = value;
  }

  /**
   * Gets the value of the 'percentage' field.
   * @return The value of the 'percentage' field.
   */
  public double getPercentage() {
    return percentage;
  }


  /**
   * Sets the value of the 'percentage' field.
   * @param value the value to set.
   */
  public void setPercentage(double value) {
    this.percentage = value;
  }

  /**
   * Gets the value of the 'startDate' field.
   * @return The value of the 'startDate' field.
   */
  public long getStartDate() {
    return startDate;
  }


  /**
   * Sets the value of the 'startDate' field.
   * @param value the value to set.
   */
  public void setStartDate(long value) {
    this.startDate = value;
  }

  /**
   * Gets the value of the 'hours' field.
   * @return The value of the 'hours' field.
   */
  public int getHours() {
    return hours;
  }


  /**
   * Sets the value of the 'hours' field.
   * @param value the value to set.
   */
  public void setHours(int value) {
    this.hours = value;
  }

  /**
   * Gets the value of the 'discountCode' field.
   * @return The value of the 'discountCode' field.
   */
  public java.lang.CharSequence getDiscountCode() {
    return discountCode;
  }


  /**
   * Sets the value of the 'discountCode' field.
   * @param value the value to set.
   */
  public void setDiscountCode(java.lang.CharSequence value) {
    this.discountCode = value;
  }

  /**
   * Creates a new ProductDiscountEvent RecordBuilder.
   * @return A new ProductDiscountEvent RecordBuilder
   */
  public static ProductDiscountEvent.Builder newBuilder() {
    return new ProductDiscountEvent.Builder();
  }

  /**
   * Creates a new ProductDiscountEvent RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new ProductDiscountEvent RecordBuilder
   */
  public static ProductDiscountEvent.Builder newBuilder(ProductDiscountEvent.Builder other) {
    if (other == null) {
      return new ProductDiscountEvent.Builder();
    } else {
      return new ProductDiscountEvent.Builder(other);
    }
  }

  /**
   * Creates a new ProductDiscountEvent RecordBuilder by copying an existing ProductDiscountEvent instance.
   * @param other The existing instance to copy.
   * @return A new ProductDiscountEvent RecordBuilder
   */
  public static ProductDiscountEvent.Builder newBuilder(ProductDiscountEvent other) {
    if (other == null) {
      return new ProductDiscountEvent.Builder();
    } else {
      return new ProductDiscountEvent.Builder(other);
    }
  }

  /**
   * RecordBuilder for ProductDiscountEvent instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<ProductDiscountEvent>
    implements org.apache.avro.data.RecordBuilder<ProductDiscountEvent> {

    private long productId;
    private double percentage;
    private long startDate;
    private int hours;
    private java.lang.CharSequence discountCode;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(ProductDiscountEvent.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.productId)) {
        this.productId = data().deepCopy(fields()[0].schema(), other.productId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.percentage)) {
        this.percentage = data().deepCopy(fields()[1].schema(), other.percentage);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.startDate)) {
        this.startDate = data().deepCopy(fields()[2].schema(), other.startDate);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.hours)) {
        this.hours = data().deepCopy(fields()[3].schema(), other.hours);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.discountCode)) {
        this.discountCode = data().deepCopy(fields()[4].schema(), other.discountCode);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
    }

    /**
     * Creates a Builder by copying an existing ProductDiscountEvent instance
     * @param other The existing instance to copy.
     */
    private Builder(ProductDiscountEvent other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.productId)) {
        this.productId = data().deepCopy(fields()[0].schema(), other.productId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.percentage)) {
        this.percentage = data().deepCopy(fields()[1].schema(), other.percentage);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.startDate)) {
        this.startDate = data().deepCopy(fields()[2].schema(), other.startDate);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.hours)) {
        this.hours = data().deepCopy(fields()[3].schema(), other.hours);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.discountCode)) {
        this.discountCode = data().deepCopy(fields()[4].schema(), other.discountCode);
        fieldSetFlags()[4] = true;
      }
    }

    /**
      * Gets the value of the 'productId' field.
      * @return The value.
      */
    public long getProductId() {
      return productId;
    }


    /**
      * Sets the value of the 'productId' field.
      * @param value The value of 'productId'.
      * @return This builder.
      */
    public ProductDiscountEvent.Builder setProductId(long value) {
      validate(fields()[0], value);
      this.productId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'productId' field has been set.
      * @return True if the 'productId' field has been set, false otherwise.
      */
    public boolean hasProductId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'productId' field.
      * @return This builder.
      */
    public ProductDiscountEvent.Builder clearProductId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'percentage' field.
      * @return The value.
      */
    public double getPercentage() {
      return percentage;
    }


    /**
      * Sets the value of the 'percentage' field.
      * @param value The value of 'percentage'.
      * @return This builder.
      */
    public ProductDiscountEvent.Builder setPercentage(double value) {
      validate(fields()[1], value);
      this.percentage = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'percentage' field has been set.
      * @return True if the 'percentage' field has been set, false otherwise.
      */
    public boolean hasPercentage() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'percentage' field.
      * @return This builder.
      */
    public ProductDiscountEvent.Builder clearPercentage() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'startDate' field.
      * @return The value.
      */
    public long getStartDate() {
      return startDate;
    }


    /**
      * Sets the value of the 'startDate' field.
      * @param value The value of 'startDate'.
      * @return This builder.
      */
    public ProductDiscountEvent.Builder setStartDate(long value) {
      validate(fields()[2], value);
      this.startDate = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'startDate' field has been set.
      * @return True if the 'startDate' field has been set, false otherwise.
      */
    public boolean hasStartDate() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'startDate' field.
      * @return This builder.
      */
    public ProductDiscountEvent.Builder clearStartDate() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'hours' field.
      * @return The value.
      */
    public int getHours() {
      return hours;
    }


    /**
      * Sets the value of the 'hours' field.
      * @param value The value of 'hours'.
      * @return This builder.
      */
    public ProductDiscountEvent.Builder setHours(int value) {
      validate(fields()[3], value);
      this.hours = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'hours' field has been set.
      * @return True if the 'hours' field has been set, false otherwise.
      */
    public boolean hasHours() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'hours' field.
      * @return This builder.
      */
    public ProductDiscountEvent.Builder clearHours() {
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'discountCode' field.
      * @return The value.
      */
    public java.lang.CharSequence getDiscountCode() {
      return discountCode;
    }


    /**
      * Sets the value of the 'discountCode' field.
      * @param value The value of 'discountCode'.
      * @return This builder.
      */
    public ProductDiscountEvent.Builder setDiscountCode(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.discountCode = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'discountCode' field has been set.
      * @return True if the 'discountCode' field has been set, false otherwise.
      */
    public boolean hasDiscountCode() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'discountCode' field.
      * @return This builder.
      */
    public ProductDiscountEvent.Builder clearDiscountCode() {
      discountCode = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ProductDiscountEvent build() {
      try {
        ProductDiscountEvent record = new ProductDiscountEvent();
        record.productId = fieldSetFlags()[0] ? this.productId : (java.lang.Long) defaultValue(fields()[0]);
        record.percentage = fieldSetFlags()[1] ? this.percentage : (java.lang.Double) defaultValue(fields()[1]);
        record.startDate = fieldSetFlags()[2] ? this.startDate : (java.lang.Long) defaultValue(fields()[2]);
        record.hours = fieldSetFlags()[3] ? this.hours : (java.lang.Integer) defaultValue(fields()[3]);
        record.discountCode = fieldSetFlags()[4] ? this.discountCode : (java.lang.CharSequence) defaultValue(fields()[4]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<ProductDiscountEvent>
    WRITER$ = (org.apache.avro.io.DatumWriter<ProductDiscountEvent>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<ProductDiscountEvent>
    READER$ = (org.apache.avro.io.DatumReader<ProductDiscountEvent>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeLong(this.productId);

    out.writeDouble(this.percentage);

    out.writeLong(this.startDate);

    out.writeInt(this.hours);

    out.writeString(this.discountCode);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.productId = in.readLong();

      this.percentage = in.readDouble();

      this.startDate = in.readLong();

      this.hours = in.readInt();

      this.discountCode = in.readString(this.discountCode instanceof Utf8 ? (Utf8)this.discountCode : null);

    } else {
      for (int i = 0; i < 5; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.productId = in.readLong();
          break;

        case 1:
          this.percentage = in.readDouble();
          break;

        case 2:
          this.startDate = in.readLong();
          break;

        case 3:
          this.hours = in.readInt();
          break;

        case 4:
          this.discountCode = in.readString(this.discountCode instanceof Utf8 ? (Utf8)this.discountCode : null);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}









