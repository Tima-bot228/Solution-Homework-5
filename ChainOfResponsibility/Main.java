interface Image {
    void displayThumbnail();
    void displayFullImage();
}

class HighResImage implements Image {
    private String filePath;

    HighResImage(String filePath) {
        this.filePath = filePath;
        loadImageFromDisk();
    }

    private void loadImageFromDisk() {
        System.out.println("Loading high-resolution image: " + filePath);
    }

    @Override
    public void displayThumbnail() {
        System.out.println("Displaying thumbnail for: " + filePath);
    }

    @Override
    public void displayFullImage() {
        System.out.println("Displaying full-resolution image: " + filePath);
    }
}

class ImageProxy implements Image {
    private String filePath;
    private HighResImage realImage;

    ImageProxy(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void displayThumbnail() {
        System.out.println("Displaying cached thumbnail for: " + filePath);
    }

    @Override
    public void displayFullImage() {
        if (realImage == null) {
            realImage = new HighResImage(filePath);
        }
        realImage.displayFullImage();
    }
}

class ImageUploader {
    private boolean isAgentLoggedIn;

    ImageUploader(boolean isAgentLoggedIn) {
        this.isAgentLoggedIn = isAgentLoggedIn;
    }

    void uploadImage(String filePath) {
        if (isAgentLoggedIn) {
            System.out.println("Uploading image: " + filePath);
        } else {
            System.out.println("Access denied. Please log in as an agent.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Image image1 = new ImageProxy("house1.jpg");
        Image image2 = new ImageProxy("villa2.jpg");

        image1.displayThumbnail();
        image2.displayThumbnail();

        image1.displayFullImage();
        image2.displayFullImage();

        ImageUploader guestUploader = new ImageUploader(false);
        guestUploader.uploadImage("new_house.jpg");

        ImageUploader agentUploader = new ImageUploader(true);
        agentUploader.uploadImage("new_villa.jpg");
    }
}
