package fr.fscf.contacts.shared.dto;

import fr.fscf.contacts.shared.dto.base.AbstractEntityDTO;
import fr.fscf.contacts.shared.util.Constraints;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Application contact DTO.
 */
public class ContactDTO extends AbstractEntityDTO<Long> {

    public interface RequiredDetailedFunctionGroup {
        // Validation group empty interface.
    }

    @NotNull
    private String name;

    @NotNull
    private String firstName;

    @NotNull
    @Pattern(regexp = Constraints.EMAIL_PATTERN, flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @NotNull
    private String phone;

    private String address;

    private String additionalAddress;

    @Pattern(regexp = Constraints.ZIP_CODE_PATTERN, flags = Pattern.Flag.CASE_INSENSITIVE)
    private String zipCode;

    private String city;

    private String email2;

    private String phone2;

    @NotNull
    private FunctionDTO function;

    @NotNull(groups = RequiredDetailedFunctionGroup.class)
    private String detailedFunction;

    @NotNull
    private StructureDTO structure;

    @Override
    public String toString() {
        return firstName + ' ' + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdditionalAddress() {
        return additionalAddress;
    }

    public void setAdditionalAddress(String additionalAddress) {
        this.additionalAddress = additionalAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public FunctionDTO getFunction() {
        return function;
    }

    public void setFunction(FunctionDTO function) {
        this.function = function;
    }

    public String getDetailedFunction() {
        return detailedFunction;
    }

    public void setDetailedFunction(String detailedFunction) {
        this.detailedFunction = detailedFunction;
    }

    public StructureDTO getStructure() {
        return structure;
    }

    public void setStructure(StructureDTO structure) {
        this.structure = structure;
    }

}
