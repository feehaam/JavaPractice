# MongoDB_Basics_With_Springboot
- MongoDB integrated with spring boot
- Two basic cruds added
- One to many relation implemented
### Here is one of the get-all API response example: 
```json
[
  {
    "id": 1,
    "title": "Introduction to MongoDB",
    "tutorName": "Dr. Smith",
    "enrolledStudents": [
      {
        "id": 12545100,
        "name": "John Doe",
        "roll": "JD001",
        "department": "Computer Science"
      }
    ]
  }
]
```