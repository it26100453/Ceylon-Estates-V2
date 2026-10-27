import java.io.*;
import java.util.ArrayList;
import java.util.List;

// This class does the actual work - reading/writing admins.txt
// Servlets call these methods instead of touching files directly

public class AdminService {

    // change this path depending on where you deploy the project
    private static final String FILE_PATH = "admins.txt";

    // CREATE - add a new admin to the file
    public boolean createAdmin(Admin admin) {
        try {
            // make sure username isn't already taken
            if (findByUsername(admin.getUsername()) != null) {
                return false;
            }

            FileWriter fw = new FileWriter(FILE_PATH, true); // true = append mode
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(admin.toFileString());
            bw.newLine();
            bw.close();
            return true;

        } catch (IOException e) {
            System.out.println("Error writing admin to file: " + e.getMessage());
            return false;
        }
    }

    // READ - get all admins from the file
    public List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return admins; // empty list if file hasn't been created yet
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    admins.add(Admin.fromFileString(line));
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading admins file: " + e.getMessage());
        }

        return admins;
    }

    // READ - find one admin by username
    public Admin findByUsername(String username) {
        List<Admin> all = getAllAdmins();
        for (Admin a : all) {
            if (a.getUsername().equalsIgnoreCase(username)) {
                return a;
            }
        }
        return null;
    }

    // READ - find by id, used for edit/delete pages
    public Admin findById(String id) {
        List<Admin> all = getAllAdmins();
        for (Admin a : all) {
            if (a.getUserId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    // UPDATE - rewrite the file with the updated admin's details
    public boolean updateAdmin(Admin updatedAdmin) {
        List<Admin> all = getAllAdmins();
        boolean found = false;

        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getUserId().equals(updatedAdmin.getUserId())) {
                all.set(i, updatedAdmin);
                found = true;
                break;
            }
        }

        if (!found) {
            return false;
        }

        return rewriteFile(all);
    }

    // DELETE - remove an admin by id and rewrite the file
    public boolean deleteAdmin(String id) {
        List<Admin> all = getAllAdmins();
        boolean removed = all.removeIf(a -> a.getUserId().equals(id));

        if (!removed) {
            return false;
        }

        return rewriteFile(all);
    }

    // helper - since text files don't support "editing a line", we just rewrite everything
    private boolean rewriteFile(List<Admin> admins) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, false)); // false = overwrite
            for (Admin a : admins) {
                bw.write(a.toFileString());
                bw.newLine();
            }
            bw.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error updating admins file: " + e.getMessage());
            return false;
        }
    }

    // simple id generator - just uses count + 1, good enough for a coursework project
    public String generateNextId() {
        List<Admin> all = getAllAdmins();
        return "A" + String.format("%03d", all.size() + 1);
    }
}
