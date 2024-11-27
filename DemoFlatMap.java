import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DemoFlatMap {

  public static class Customer {
    private Address address;

    public Optional<Address> getAddress() {
      return Optional.ofNullable(this.address);
    }

    public Customer(Address address) {
      this.address = address;
    }
  }

  public static class Address {
    private String add1;
    private String add2;

    public Address(String add1, String add2) {
      this.add1 = add1;
      this.add2 = add2;
    }

    public String getFullAddress() {
      return this.add1.concat(add2);
    }

    @Override
    public String toString() {
      return this.add1 + this.add2;
    }
  }

  public static void main(String[] args) {
    /**
     * [CustomerA[AddressA[add1,add2]],
     * 
     * CustomerB[AddressB[add1,add2]], ]
     */
    List<Customer> customers =
        Arrays.asList(new Customer(new Address("123 Main St", " Apt 4B")), //
            new Customer(new Address("456 Elm St", " Suite 2A"))
        // new Customer(null)//
        );

    // [[123,456] ,[789,101112]] -> [123,456,789,101112]
    // List<List<String> -> List<String>
    
    List<String> fullAddresses = customers.stream()//
        .map(Customer::getAddress)
        .flatMap(optAddress -> optAddress
            .map(address -> Stream.of(address.getFullAddress()))
            .orElseGet(Stream::empty))
        .collect(Collectors.toList());

    // Print the result
    System.out.println("Full Addresses: " + fullAddresses);
  }
}
