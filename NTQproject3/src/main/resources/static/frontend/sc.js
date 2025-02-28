const questions = [];
let examTitle = '';
let examDescription = '';

// Thêm câu hỏi
document.getElementById('addQuestionForm').addEventListener('submit', function(e) {
    e.preventDefault();

    const questionContent = document.getElementById('questionContent').value;
    const answerA = document.getElementById('answerA').value;
    const answerB = document.getElementById('answerB').value;
    const answerC = document.getElementById('answerC').value;
    const answerD = document.getElementById('answerD').value;
    const correctAnswer = document.getElementById('correctAnswer').value;

    const question = {
        content: questionContent,
        answers: {
            A: answerA,
            B: answerB,
            C: answerC,
            D: answerD
        },
        correctAnswer: correctAnswer
    };

    questions.push(question);

    displayQuestions();
    document.getElementById('addQuestionForm').reset();
});

// Hiển thị danh sách câu hỏi
function displayQuestions() {
    const questionsList = document.getElementById('questionsList');
    questionsList.innerHTML = '';

    questions.forEach((q, index) => {
        const li = document.createElement('li');
        li.innerHTML = `
            <strong>Câu ${index + 1}:</strong> ${q.content}
            <br><strong>Đáp án A:</strong> ${q.answers.A} | 
            <strong>Đáp án B:</strong> ${q.answers.B} | 
            <strong>Đáp án C:</strong> ${q.answers.C} | 
            <strong>Đáp án D:</strong> ${q.answers.D} | 
            <strong>Đáp án đúng:</strong> ${q.correctAnswer}
            <button class="delete" onclick="deleteQuestion(${index})">Xóa</button>
        `;
        questionsList.appendChild(li);
    });
}

// Xóa câu hỏi
function deleteQuestion(index) {
    questions.splice(index, 1);
    displayQuestions();
}

// Tạo đề thi
document.getElementById('generateExamBtn').addEventListener('click', function() {
    if (questions.length < 40) {
        alert("Đề thi phải có ít nhất 40 câu hỏi.");
        return;
    }

    const examList = document.getElementById('examList');
    examList.innerHTML = '';

    for (let i = 0; i < 40; i++) {
        const li = document.createElement('li');
        li.textContent = `Câu ${i + 1}: ${questions[i].content}`;
        examList.appendChild(li);
    }

    document.getElementById('generatedExam').style.display = 'block';
});

// Tạo đề thi mới
document.getElementById('createExamForm').addEventListener('submit', function(e) {
    e.preventDefault();

    examTitle = document.getElementById('examTitle').value;
    examDescription = document.getElementById('examDescription').value;

    alert('Đề thi đã được tạo! Bạn có thể thêm câu hỏi vào đề thi.');
    document.getElementById('createExamForm').reset();
});
