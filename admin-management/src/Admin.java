// Admin class - handles the admin-side of the real estate portal
// Extends User so it gets username/password/email for free (inheritance)

public class Admin extends User {

    private String role;          // e.g. "SuperAdmin" or "Admin"
    private String permissions;   // simple comma separated list for now, e.g. "approveListing,manageUsers"
    private String lastLogin;

    public Admin(String userId, String username, String password, String email, String role, String permissions) {
        super(userId, username, password, email);
        this.role = role;
        this.permissions = permissions;
        this.lastLogin = "Never";
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    // abstraction - admin only actions, regular users don't get these
    public boolean approveListing(String listingId) {
        // in the full system this would talk to the Property Management component
        // for now just simulate the approval and return true
        System.out.println("Listing " + listingId + " approved by " + username);
        return true;
    }

    public boolean banUser(String targetUserId) {
        System.out.println("User " + targetUserId + " banned by admin " + username);
        return true;
    }

    // overriding the parent's abstract method - polymorphism in action
    @Override
    public String getDashboardInfo() {
        if (role.equalsIgnoreCase("SuperAdmin")) {
            return "Super Admin Dashboard - Full system access, manage other admins";
        } else {
            return "Admin Dashboard - Manage listings and users";
        }
    }

    @Override
    public String toFileString() {
        // format: id|username|password|email|role|permissions|lastLogin
        return userId + "|" + username + "|" + password + "|" + email + "|" + role + "|" + permissions + "|" + lastLogin;
    }

    // rebuild an Admin object from a line read out of admins.txt
    public static Admin fromFileString(String line) {
        String[] parts = line.split("\\|");
        Admin a = new Admin(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
        if (parts.length > 6) {
            a.setLastLogin(parts[6]);
        }
        return a;
    }
}
