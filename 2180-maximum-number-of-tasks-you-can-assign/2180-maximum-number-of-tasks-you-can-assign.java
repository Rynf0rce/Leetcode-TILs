class Solution {
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        Arrays.sort(workers);
        
        int left = 0;
        int right = Math.min(tasks.length, workers.length);
        
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (canAssign(tasks, workers, pills, strength, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        
        return left;
    }
    
    private boolean canAssign(int[] tasks, int[] workers, int pills, int strength, int k) {
        int[] selectedTasks = new int[k];
        for (int i = 0; i < k; i++) {
            selectedTasks[i] = tasks[i];
        }
        
        TreeMap<Integer, Integer> availableWorkers = new TreeMap<>();
        for (int worker : workers) {
            availableWorkers.put(worker, availableWorkers.getOrDefault(worker, 0) + 1);
        }
        
        for (int i = k - 1; i >= 0; i--) {
            int task = selectedTasks[i];
            
            Integer workerWithoutPill = availableWorkers.ceilingKey(task);
            if (workerWithoutPill != null) {
                removeWorker(availableWorkers, workerWithoutPill);
                continue;
            }
            
            if (pills > 0) {
                int requiredStrength = Math.max(0, task - strength);
                Integer workerWithPill = availableWorkers.ceilingKey(requiredStrength);
                
                if (workerWithPill != null) {
                    removeWorker(availableWorkers, workerWithPill);
                    pills--;
                    continue;
                }
            }
            
            return false;
        }
        
        return true;
    }
    
    private void removeWorker(TreeMap<Integer, Integer> workers, int worker) {
        int count = workers.get(worker);
        if (count == 1) {
            workers.remove(worker);
        } else {
            workers.put(worker, count - 1);
        }
    }
}