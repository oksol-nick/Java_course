import com.example.demo.FirstBean
import com.example.demo.PrototypeBean
import com.example.demo.RequestScopeBean
import com.example.demo.SecondBean

beans {

    prototypeBean(PrototypeBean) {
        bean -> bean.scope = "prototype"
    }

    secondBean(SecondBean) {

        bean -> bean.constructorArgs = [ref(prototypeBean)]
    }

    firstBean(FirstBean) {

        bean -> bean.constructorArgs = [ref(prototypeBean)]

    }

    requestScopeBean(RequestScopeBean) {
        scope -> scope = "request"
    }
}