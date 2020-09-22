package com.johnmbiya.notekeeperkotlin

object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initializeCourses()
        initializeNotes()
    }

    private fun initializeCourses() {
        var course = CourseInfo("android_intents", "Android Programming with Intents")
        courses.set(course.courseId, course)

        course = CourseInfo(courseId = "android_async", title = "Android Async Programming and Services")
        courses.set(course.courseId, course)

        course = CourseInfo(title = "Java Fundamentals: The Java Language", courseId = "java_lang")
        courses.set(course.courseId, course)

        course = CourseInfo("java_core", "Java Fundamentals: The Core Platform")
        courses.set(course.courseId, course)
    }

    private fun initializeNotes() {

        var course = courses["android_intents"]!!
        var note = NoteInfo(course, "Dynamic intent resolution",
            "Wow, intents allow components to be resolved at runtime")
        note.comments.add(NoteComment("Jeff", "Excellent Point!", System.currentTimeMillis()))
        note.comments.add(NoteComment("Dave", "We should review this", System.currentTimeMillis() - 5000))
        note.comments.add(NoteComment("Lucy", "This is good to know", System.currentTimeMillis() - 10000))
        notes.add(note)
        note = NoteInfo(course, "Delegating intents",
            "PendingIntents are powerful; they delegate much more than just a component invocation")
        note.comments.add(NoteComment("Jeff", "Excellent Point!", System.currentTimeMillis()))
        note.comments.add(NoteComment("Dave", "We should review this", System.currentTimeMillis() - 5000))
        note.comments.add(NoteComment("Lucy", "This is good to know", System.currentTimeMillis() - 10000))
        notes.add(note)

        course = courses["android_async"]!!
        note = NoteInfo(course, "Service default threads",
            "Did you know that by default an Android Service will tie up the UI thread?")
        note.comments.add(NoteComment("Jeff", "Excellent Point!", System.currentTimeMillis()))
        note.comments.add(NoteComment("Dave", "We should review this", System.currentTimeMillis() - 5000))
        note.comments.add(NoteComment("Lucy", "This is good to know", System.currentTimeMillis() - 10000))
        notes.add(note)
        note = NoteInfo(course, "Long running operations",
            "Foreground Services can be tied to a notification icon")
        note.comments.add(NoteComment("Jeff", "Excellent Point!", System.currentTimeMillis()))
        note.comments.add(NoteComment("Dave", "We should review this", System.currentTimeMillis() - 5000))
        note.comments.add(NoteComment("Lucy", "This is good to know", System.currentTimeMillis() - 10000))
        notes.add(note)

        course = courses["java_lang"]!!
        note = NoteInfo(course, "Parameters",
            "Leverage variable-length parameter lists")
        note.comments.add(NoteComment("Jeff", "Excellent Point!", System.currentTimeMillis()))
        note.comments.add(NoteComment("Dave", "We should review this", System.currentTimeMillis() - 5000))
        note.comments.add(NoteComment("Lucy", "This is good to know", System.currentTimeMillis() - 10000))
        notes.add(note)
        note = NoteInfo(course, "Anonymous classes",
            "Anonymous classes simplify implementing one-use types")
        note.comments.add(NoteComment("Jeff", "Excellent Point!", System.currentTimeMillis()))
        note.comments.add(NoteComment("Dave", "We should review this", System.currentTimeMillis() - 5000))
        note.comments.add(NoteComment("Lucy", "This is good to know", System.currentTimeMillis() - 10000))
        notes.add(note)

        course = courses["java_core"]!!
        note = NoteInfo(course, "Compiler options",
            "The -jar option isn't compatible with with the -cp option")
        note.comments.add(NoteComment("Jeff", "Excellent Point!", System.currentTimeMillis()))
        note.comments.add(NoteComment("Dave", "We should review this", System.currentTimeMillis() - 5000))
        note.comments.add(NoteComment("Lucy", "This is good to know", System.currentTimeMillis() - 10000))
        notes.add(note)
        note = NoteInfo(course, "Serialization",
            "Remember to include SerialVersionUID to assure version compatibility")
        note.comments.add(NoteComment("Jeff", "Excellent Point!", System.currentTimeMillis()))
        note.comments.add(NoteComment("Dave", "We should review this", System.currentTimeMillis() - 5000))
        note.comments.add(NoteComment("Lucy", "This is good to know", System.currentTimeMillis() - 10000))
        notes.add(note)
    }
}