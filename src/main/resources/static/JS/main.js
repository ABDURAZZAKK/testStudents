var studentAPI = Vue.resource('/student{/id}');
var testAPI = Vue.resource('/tests{/id}');


Vue.component("login",{

    template: `<div><input id="login" type='text' placeholder="Введите ФИО" />
                <button @click="trigger">Перейти к тесту</button></div>
    `,
    methods:{
        trigger: function(){
            this.$emit('create_student')
        }
    }

})


Vue.component("tests-list",{
    props: ["tests"],
    template: `
                <div>
                    <h1>Выберите тест</h1>
                        <div v-for="test in tests">
                            <button :id="test.id" :name="test.title" @click='open_test'>{{test.title}}</button>
                        </div>
                </div>
    `,
    created: function(){
        testAPI.get().then(result => result.json())
                     .then(data => data.forEach(test => this.tests.push(test)))
    },
    methods:{
        open_test: function(event){
            this.$emit('change_test',{"id":event.target.id, "title":event.target.name})
            this.$emit('change_component','questions-list')
        }
    }


})


Vue.component("questions-list",{
    data(){
        return{
            answers: {}
        }
    },
    props: ["test_id", "test_title", "tests", 'payment'],
    template: `
                <div>
                    <h1>{{test_title}}</h1>
                    <div v-for="quest in tests">
                        <h2>{{quest.text}}</h2>

                            <div v-for="answer in quest.answers">
                                    <input type="radio" :id="answer.id" :name="quest.id" @change="add_answer">
                                    <label :for="answer.id"><span>{{answer.text}}</span></label>
                            </div>
                    </div>
                    <h2 v-if="payment !== null">Верно: {{payment.num_correct}} из {{payment.num_questions}}</h2>
                    <button  v-if="payment === null" @click="give_answers">ЗАВЕРШИТЬ ТЕСТ</button>

                </div>
    `,
    created: function(){
        testAPI.get({id: this.test_id},{title: this.test_title}).then(result => result.json())
                                        .then(data => data.forEach(test => this.tests.push(test)))
    },
    methods: {
        add_answer: function(event){
            this.answers[event.target.name] = event.target.id
        },
        give_answers: function(){
            this.$emit("scoring",this.answers)
        }
    }

})
//data.forEach(test => this.tests.push(test))
var app = new Vue({
    el: '#app',
    data: {
        selected_component: 'login',
        student_id: null,
        tests: [],
        test_id: null,
        test_title: null,
        payment: null

    },
     computed: {
       currentTabComponent: function() {
         return this.selected_component;
       }
     },
    methods:{
         create_student: function(){
            name = document.getElementById('login').value
            studentAPI.save({},{"username":name})
                                               .then(result => result.json())
                                               .then(data => this.student_id = data.id)
            this.change_component("tests-list")

       },
        change_test: function(test){

            this.test_id = test['id']
            this.test_title = test['title']

        },
        change_component: function(comp){
            this.tests = []
            this.selected_component = comp

        },
        scoring: function(answers_list){
           var questions= [], answers=[]
           for(i in answers_list){
                questions.push({"id":i})
                answers.push({"id":answers_list[i]})
           }
           testAPI.save({},{"student":{"id":this.student_id},
                            "test":{"id":this.test_id},
                            "questions": questions,
                            "answers":answers}).then(result => result.json())
                                                                    .then(data => {this.payment = {
                                                                                            correct_list: data["list_of_correct_answers"],
                                                                                            num_correct: data["num_of_correct_answers"],
                                                                                            num_questions: data["num_of_questions_answered"]}
                                                                    })
        }


    }

});