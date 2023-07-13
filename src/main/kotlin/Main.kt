import kotlin.jvm.internal.AdaptedFunctionReference

data class User(val id: Long,
                var firstName: String,
                var age: Int? = null,
                var lastName: String,
                var gender: String? = null,
                var address: List<Address>
)
data class Address(val id: Long,
                   var streetCode: String)

data class UserRequest(var firstName: String,
                       var age: Int? = null,
                       var lastName: String,
                       var gender: String? = null,
                       var address: List<AddressRequest> )
data class AddressRequest(var streetCode: String)

val userList= mutableListOf<User>()
var id:Long=0


fun AddressRequest.toModel()=Address(id= id,streetCode=streetCode)

fun UserRequest.toModel()=User(id=++id ,firstName=firstName,age=age,lastName=lastName,gender=gender,address=address.map { it.toModel() })


fun create(users:UserRequest){
    userList.add(users.toModel())
}

//Getting the record of users
fun getFun(value: String): List<User> {
    val getUserList = userList
    val getUserDetails = getUserList.filter { it.firstName.contains(value) }
    return getUserDetails
}

//Updating record with new values
fun updateFun(update: UserRequest, unqId:Long): MutableList<User> {
    var userModel= update.toModel()
    userList.forEach {it ->
        if (it.id == unqId) {
            it.firstName=userModel.firstName
            it.lastName=userModel.lastName
            it.age=userModel.age
            it.gender=userModel.gender
        }
    }

    return userList
}
//deleting a record for the user List
fun deleteFun(delId:Long){
    val getUserList = userList
    getUserList.forEach{it->
        if(it.id==delId){
            getUserList.remove(it)
        }
    }
}
//creating user records
fun createUser(){
    val person1= create(UserRequest("nasreen", 22,"banu", "Female", listOf<AddressRequest>(AddressRequest("600096"))))
    val person2 = create(UserRequest("celina", 22,"helen", "Female", listOf<AddressRequest>(AddressRequest("600097"))))
    val person3 = create(UserRequest("umar", 22,"fariz", "male", listOf<AddressRequest>(AddressRequest("600086"))))
//    val person4 = create(UserRequest("lokesh", 22,"pg", "male", listOf<AddressRequest>(AddressRequest("600076"))))

}
//main function
fun main(args: Array<String>) {
    createUser()
    println("Created User details and stored in a list. And the list is:")
    println(userList)
    println("")
    println("Getting user record using First name:")
    println(getFun("celina"))
    println("")
    val update_User= UserRequest("nadhira", 40,"banu", "Female", listOf<AddressRequest>(AddressRequest("600072")))
    println("Updated list")
    println(updateFun(update_User,1))
    println("")
    println("Deleted a record and the updated list is:")
    deleteFun(2)
    println(userList)
    println("")
}


