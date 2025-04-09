package lc;

import java.util.*;


public class FileSystem {
    static class Node {
        String name;
        Map<String, Node> children = new TreeMap<>();

        Node(String name) {
            this.name = name;
        }

        void print(String indent) {
            System.out.println(indent + name);
            for (Node child : children.values()) {
                child.print(indent + "  ");
            }
        }
    }

    private final Node root = new Node("");

    public void create(String path) {
        String[] parts = path.split("/");
        Node current = root;
        for (String part : parts) {
            current.children.putIfAbsent(part, new Node(part));
            current = current.children.get(part);
        }
    }

    public void move(String source, String destination) {
        Node srcParent = findParent(source);
        if (srcParent == null) return;

        String name = lastPart(source);
        Node toMove = srcParent.children.remove(name);
        if (toMove == null) return;

        Node destNode = find(destination);
        if (destNode != null) {
            destNode.children.put(name, toMove);
        }
    }

    public void delete(String path) {
        Node parent = findParent(path);
        String name = lastPart(path);
        if (parent == null || !parent.children.containsKey(name)) {
            String parentPath = path.contains("/") ? path.substring(0, path.lastIndexOf('/')) : "";
            System.out.println("Cannot delete " + path + " - " +
                    (parent == null ? parentPath + " does not exist" : path + " does not exist"));
            return;
        }
        parent.children.remove(name);
    }

    public void list() {
        for (Node child : root.children.values()) {
            child.print("");
        }
    }

    private Node find(String path) {
        String[] parts = path.split("/");
        Node current = root;
        for (String part : parts) {
            if (!current.children.containsKey(part)) return null;
            current = current.children.get(part);
        }
        return current;
    }

    private Node findParent(String path) {
        int idx = path.lastIndexOf('/');
        String parentPath = (idx == -1) ? "" : path.substring(0, idx);
        return find(parentPath);
    }

    private String lastPart(String path) {
        int idx = path.lastIndexOf('/');
        return (idx == -1) ? path : path.substring(idx + 1);
    }

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter commands. Type EXIT to quit.");

        while (true) {
            String line = in.nextLine().trim();
            if (line.equalsIgnoreCase("EXIT")) break;

            if (line.startsWith("CREATE ")) {
                fs.create(line.substring(7));
            } else if (line.startsWith("MOVE ")) {
                String[] parts = line.substring(5).split(" ");
                if (parts.length == 2) fs.move(parts[0], parts[1]);
            } else if (line.startsWith("DELETE ")) {
                fs.delete(line.substring(7));
            } else if (line.equals("LIST")) {
                fs.list();
            } else {
                System.out.println("Unknown command");
            }
        }

        in.close();
    }
}
