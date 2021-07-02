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
            console.log(`event: ${event.target.id}`)
            console.log(`event: ${event.target.name}`)
            this.$emit('change_test',{"id":event.target.id, "title":event.target.name})
            this.$emit('change_component','questions-list')
        }
    }


})


Vue.component("questions-list",{
    props: ["test_id", "test_title", "tests"],
    template: `
                <div>
                    <h1>{{test_title}}</h1>
                    <div v-for="quest in tests">
                        <h2>{{quest.text}}</h2>
                            <div v-for="answer in quest.answers">
                                    <input type="radio" id="answer.id" name="quest.id">
                                    <label for="answer.id"><span>{{answer.text}}</span></label>
                            </div>
                    </div>
                </div>
    `,
    created: function(){
        console.log(`get: ${this.test_id}`)
        testAPI.get({id: this.test_id},{title: this.test_title}).then(result => result.json())
                                        .then(data => console.log(data))
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
        test_title: null
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
                                               .then(data => {this.student_id = data.id
                                                              console.log(this.student_id)
                                               })
            this.change_component("tests-list")

       },
        change_test: function(test){
            console.log(`test: ${test}`)
            this.test_id = test['id']
            this.test_title = test['title']
            console.log(this.test_title)
            console.log(this.test_id)
        },
        change_component: function(comp){
            this.tests = []
            this.selected_component = comp

        }

    }

});