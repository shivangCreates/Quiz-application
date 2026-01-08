import java.util.*;
import java.io.*;

public class CodeGeneratorApp {
    
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== Interactive Code Generator ===\n");
        
        // Create HashMap to store user inputs
        HashMap<String, String> variables = new HashMap<>();
        
        // Get user inputs
        System.out.print("Enter class name: ");
        variables.put("CLASS_NAME", scanner.nextLine());
        
        System.out.print("Enter field name: ");
        String fieldName = scanner.nextLine();
        variables.put("FIELD_NAME", fieldName);
        
        System.out.print("Enter field type (int, String, double): ");
        variables.put("FIELD_TYPE", scanner.nextLine());
        
        // Calculate getter/setter names
        String capitalized = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        variables.put("GETTER_NAME", "get" + capitalized);
        variables.put("SETTER_NAME", "set" + capitalized);
        
        // Generate code
        String code = generateClass(variables);
        
        // Show preview
        System.out.println("\n📄 Generated Code:");
        System.out.println("-".repeat(40));
        System.out.println(code);
        System.out.println("-".repeat(40));
        
        // Ask to save
        System.out.print("\nSave to file? (y/n): ");
        String choice = scanner.nextLine();
        
        if (choice.equalsIgnoreCase("y")) {
            saveToFile(variables.get("CLASS_NAME"), code);
        }
        
        System.out.println("\nGoodbye!");
    }
    
    public static String generateClass(HashMap<String, String> vars) {
        // Simple template
        String template = 
            "public class {{CLASS_NAME}} {\n" +
            "    private {{FIELD_TYPE}} {{FIELD_NAME}};\n\n" +
            "    public {{CLASS_NAME}}() {\n" +
            "        // Default constructor\n" +
            "    }\n\n" +
            "    public {{FIELD_TYPE}} {{GETTER_NAME}}() {\n" +
            "        return this.{{FIELD_NAME}};\n" +
            "    }\n\n" +
            "    public void {{SETTER_NAME}}({{FIELD_TYPE}} {{FIELD_NAME}}) {\n" +
            "        this.{{FIELD_NAME}} = {{FIELD_NAME}};\n" +
            "    }\n" +
            "}";
        
        // Replace all placeholders
        for (Map.Entry<String, String> entry : vars.entrySet()) {
            String placeholder = "{{" + entry.getKey() + "}}";
            template = template.replace(placeholder, entry.getValue());
        }
        
        return template;
    }
    
    public static void saveToFile(String className, String content) {
        try {
            String filename = className + ".java";
            FileWriter writer = new FileWriter(filename);
            writer.write(content);
            writer.close();
            System.out.println("✅ Saved as: " + filename);
        } catch (IOException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
