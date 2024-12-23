import requests
import random

# URL and Authorization header
url = "http://localhost:8088/api/v1/lessons"
headers = {
    "Authorization": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6ImFsbyIsInN1YiI6ImFsbyIsImV4cCI6MTczNDY2NTc5MH0.rg8p_RmqjAlnI4RYEdoPM6fmNsyTO6ZUyBLmEbQXCKhdRQbXuKK0zZNmceDlQYHx8huH76qP57f19Mf1BXNPQw",
    "Content-Type": "application/json",
}


# Function to generate random titles and descriptions
def generate_lesson_data(chapter_id, count=30):
    lessons = []
    for i in range(count):
        title = f"Lesson Title {i + 1}"
        description = f"This is a description for lesson {i + 1}, and it is amazing!"
        lesson = {"title": title, "description": description, "chapter_id": chapter_id}
        lessons.append(lesson)
    return lessons


# Generate lessons data
chapter_id = 1
lessons_data = generate_lesson_data(chapter_id)

# Make POST requests
for lesson in lessons_data:
    try:
        response = requests.post(url, headers=headers, json=lesson)
        if response.status_code == 201:
            print(f"Lesson created successfully: {lesson['title']}")
        else:
            print(
                f"Failed to create lesson: {lesson['title']}. Status code: {response.status_code}. Response: {response.text}"
            )
    except Exception as e:
        print(f"Error occurred while creating lesson {lesson['title']}: {e}")
