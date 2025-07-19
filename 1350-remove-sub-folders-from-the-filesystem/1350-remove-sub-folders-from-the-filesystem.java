class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        
        List<String> result = new ArrayList<>();
        
        for (String currentFolder : folder) {
            if (result.isEmpty() || !isSubfolder(currentFolder, result.get(result.size() - 1))) {
                result.add(currentFolder);
            }
        }
        return result;
    }
    
    private boolean isSubfolder(String currentFolder, String parentFolder) {
        return currentFolder.startsWith(parentFolder) && currentFolder.length() > parentFolder.length() &&currentFolder.charAt(parentFolder.length()) == '/';
    }
}