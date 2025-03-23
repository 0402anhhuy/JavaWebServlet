package Payload;

@SuppressWarnings("unused")
public class ResponseData {
    private boolean isDeleteSuccess;
    private boolean isInsertSuccess;
    private String description;
    private Object data;

    public boolean isDeleteSuccess() {
        return isDeleteSuccess;
    }

    public void setDeleteSuccess(boolean deleteSuccess) {
        isDeleteSuccess = deleteSuccess;
    }

    public boolean isInsertSuccess(){
        return isInsertSuccess;
    }

    public void setInsertSuccess(boolean insertSuccess){
        isInsertSuccess = insertSuccess;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
