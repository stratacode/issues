class ExtInnerIface implements InnerIface {
    static class DefaultInner1 implements Inner1 {
    }

    class DefaultInner2 extends Inner2 {
    }

    interface Inner3 extends Inner1 {
    }
}
