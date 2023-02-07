package training.stage2.framework.model;

import java.util.Objects;

public class TestingSet {
    private String form_number_of_instance;
    private String form_os_type;
    private String form_class_type;
    private String form_instance_series;
    private String form_instance_type;
    private String form_gpu_number;
    private String form_gpu_type;
    private String form_ssd_capacity;
    private String form_location;
    private String form_usage;

    public TestingSet(String form_number_of_instance,
                      String form_os_type,
                      String form_class_type,
                      String form_instance_series,
                      String form_instance_type,
                      String form_gpu_number,
                      String form_gpu_type,
                      String form_ssd_capacity,
                      String form_location,
                      String form_usage) {
        this.form_number_of_instance = form_number_of_instance;
        this.form_os_type = form_os_type;
        this.form_class_type = form_class_type;
        this.form_instance_series = form_instance_series;
        this.form_instance_type = form_instance_type;
        this.form_gpu_number = form_gpu_number;
        this.form_gpu_type = form_gpu_type;
        this.form_ssd_capacity = form_ssd_capacity;
        this.form_location = form_location;
        this.form_usage = form_usage;
    }

    public String getFormNumberOfInstance() {
        return form_number_of_instance;
    }

    public String getFormOsType() {
        return form_os_type;
    }

    public String getFormClassType() {
        return form_class_type;
    }

    public String getFormInstanceSeries() {
        return form_instance_series;
    }

    public String getFormInstanceType() {
        return form_instance_type;
    }

    public String getFormGpuNumber() {
        return form_gpu_number;
    }

    public String getFormGpuType() {
        return form_gpu_type;
    }

    public String getFormSsdCapacity() {
        return form_ssd_capacity;
    }

    public String getFormLocation() {
        return form_location;
    }

    public String getFormUsage() {
        return form_usage;
    }

    @Override
    public String toString() {
        return "TestingSet{" +
                "form_number_of_instance='" + form_number_of_instance + '\'' +
                ", form_os_type='" + form_os_type + '\'' +
                ", form_class_type='" + form_class_type + '\'' +
                ", form_instance_series='" + form_instance_series + '\'' +
                ", form_instance_type='" + form_instance_type + '\'' +
                ", form_gpu_number='" + form_gpu_number + '\'' +
                ", form_gpu_type='" + form_gpu_type + '\'' +
                ", form_ssd_capacity='" + form_ssd_capacity + '\'' +
                ", form_location='" + form_location + '\'' +
                ", form_usage='" + form_usage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestingSet that = (TestingSet) o;
        return Objects.equals(form_number_of_instance, that.form_number_of_instance) &&
                Objects.equals(form_os_type, that.form_os_type) &&
                Objects.equals(form_class_type, that.form_class_type) &&
                Objects.equals(form_instance_series, that.form_instance_series) &&
                Objects.equals(form_instance_type, that.form_instance_type) &&
                Objects.equals(form_gpu_number, that.form_gpu_number) &&
                Objects.equals(form_gpu_type, that.form_gpu_type) &&
                Objects.equals(form_ssd_capacity, that.form_ssd_capacity) &&
                Objects.equals(form_location, that.form_location) &&
                Objects.equals(form_usage, that.form_usage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(form_number_of_instance,
                form_os_type, form_class_type,
                form_instance_series,
                form_instance_type,
                form_gpu_number,
                form_gpu_type,
                form_ssd_capacity,
                form_location,
                form_usage);
    }
}
