Android Installation and Setup

Step 1: Install Android Studio  
Download Android Studio from the official website and follow the installation instructions for your OS (Windows, macOS, or Linux). Configure the SDK and set up an Android Virtual Device (AVD) or connect a physical Android device for testing.

Step 2: Create a New Android Project  
In Android Studio, select "New Project" and choose "Empty Activity." Name your app (e.g., "LoginApp"), ensure the language is Java, and check that the SDK version is compatible. Click Finish to create the project.

Step 3: Access Project Files  
Once the project is created, navigate to the following key files:
- activity_main.xml (res/layout folder): Defines the login screen UI.
- MainActivity.java (java folder): Handles login logic.

Experiment 1: Login App

Key features include:
- Credential Validation: Validates username and password, ensuring passwords meet certain criteria.
- Error Handling: Displays error messages for incorrect login attempts and temporarily locks accounts after several failed tries.
- Session Management: Secure tokens for session management, allowing persistent login.
- Password Security: Passwords are hashed before storage.
- Password Recovery: "Forgot Password" option for resetting passwords via email.

Users log in with a username and password. Upon success, they are redirected to the main app screen; otherwise, they receive an error message. After multiple failed attempts, accounts are temporarily locked, and password recovery is available through email.

Contributing  
Contributions are welcome! For details, see the CONTRIBUTING.md file.
