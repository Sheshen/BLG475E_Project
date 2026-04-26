import json
import re

target_ids = {
    "Java/3", "Java/7", "Java/11", "Java/18", "Java/24", "Java/27", "Java/31", "Java/48", "Java/51", "Java/52", 
    "Java/1", "Java/6", "Java/9", "Java/10", "Java/17", "Java/19", "Java/20", "Java/25", "Java/36", "Java/68", 
    "Java/13", "Java/32", "Java/39", "Java/40", "Java/59", "Java/63", "Java/65", "Java/67", "Java/69", "Java/70"
}

output = []

with open("humaneval_java.jsonl", "r") as f:
    for line in f:
        data = json.loads(line)
        if data["task_id"] in target_ids:
            # extract name from prompt
            # e.g., public boolean hasCloseElements(List<Double> numbers, double threshold) {
            match = re.search(r'public\s+(?:static\s+)?[\w<>,\[\]\s]+\s+(\w+)\s*\(', data["prompt"])
            name = match.group(1) if match else "unknown"
            
            output.append({
                "task_id": data["task_id"],
                "name": name,
                "prompt": data["prompt"],
                "canonical_solution": data["canonical_solution"]
            })

# Ensure the ordering is similar to what was requested or just dump
# Let's sort them by the target_ids order or just task_id
output.sort(key=lambda x: int(x["task_id"].split("/")[1]))

with open("selection.json", "w") as f:
    json.dump(output, f, indent=4)

