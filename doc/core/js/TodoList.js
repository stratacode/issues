
// Generated JS from Java: sc.html.tag.HtmlPage -----
function sc_html_tag_HtmlPage() {   this.head = null;
   this.body = null;
   this.script_Repeat = null;

   js_HtmlPage.call(this);
   this._sc_html_tag_HtmlPageInit();
}

var _c, sc_html_tag_HtmlPage_c = _c = sc_newClass("sc.html.tag.HtmlPage", sc_html_tag_HtmlPage, js_HtmlPage, null);

_c._A_URL = {page: true, subTypesOnly: true};
_c._A_Scope = {name: "session"};

_c.getObjChildren = function (create)  {
   if (arguments.length == 0) return;
   if (create) {
      return[ this.getHead(), this.getBody(), this.getScript_Repeat() ];
   }
   else {
      return[ (this.head), (this.body), (this.script_Repeat) ];
   }
}
_c.outputBody = function (out)  {
   out.append("\n   ");
   this.getHead().outputTag(out);
   out.append("\n   ");
   this.getBody().outputTag(out);
   out.append("\n   ");
   this.getScript_Repeat().outputTag(out);
   out.append(" \n");
}
_c.outputStartTag = function (out)  {
   out.append("<!DOCTYPE html>");
   out.append("<html>");
}
_c.getHead = function ()  {
   if (this.head === null) {
      var _head = new sc_html_tag_HtmlPage_head(this);
      this.head = _head;
      sc_DynUtil_c.addDynInnerObject("sc.html.tag.HtmlPage.head", _head, this);
      return _head;
   }
   else
      return this.head;
}
_c.getBody = function ()  {
   if (this.body === null) {
      var _body = new sc_html_tag_HtmlPage_body(this);
      this.body = _body;
      sc_DynUtil_c.addDynInnerObject("sc.html.tag.HtmlPage.body", _body, this);
      return _body;
   }
   else
      return this.body;
}
_c.getScript_Repeat = function ()  {
   if (this.script_Repeat === null) {
      var _script_Repeat = new sc_html_tag_HtmlPage_script_Repeat(this);
      this.script_Repeat = _script_Repeat;
      sc_DynUtil_c.addDynInnerObject("sc.html.tag.HtmlPage.script_Repeat", _script_Repeat, this);
      return _script_Repeat;
   }
   else
      return this.script_Repeat;
}

_c._sc_html_tag_HtmlPageInit = function() {
}

// Generated JS from Java: sc.html.tag.Head -----
function sc_html_tag_Head() {
   js_Head.call(this);
}

var _c, sc_html_tag_Head_c = _c = sc_newClass("sc.html.tag.Head", sc_html_tag_Head, js_Head, null);

_c.outputStartTag = function (out)  {
   out.append("<head>");
}
_c.outputBody = function (out)  {
   out.append("\n");
}


// Generated JS from Java: sc.html.tag.HtmlPage.head -----
function sc_html_tag_HtmlPage_head(_outer) {
   this._outer1 = _outer;
   if (this.outer === undefined) this.outer = _outer;
   this.headStartTagTxt0 = null;

   sc_html_tag_Head.call(this);
   this._sc_html_tag_HtmlPage_headInit();
}

var _c, sc_html_tag_HtmlPage_head_c = _c = sc_newInnerObj("sc.html.tag.HtmlPage.head", sc_html_tag_HtmlPage_head, sc_html_tag_HtmlPage, sc_html_tag_Head, null);

_c._A_TypeSettings = {objectType: true};

_c.outputStartTag = function (out)  {
   out.append(this.getHeadStartTagTxt0());
}
_c.outputBody = function (out)  {
   sc_html_tag_Head_c.outputBody.call(this, out);
   out.append("\n      <script type='text/javascript'>\n         var sc_windowId = ");
   out.append(sc_PTypeUtil_c.getWindowId());
   out.append(";\n      </script>\n   ");
}
_c.getHeadStartTagTxt0 = function ()  {
   return this.headStartTagTxt0;
}
_c.setHeadStartTagTxt0 = function (_headStartTagTxt0)  {
   this.headStartTagTxt0 = _headStartTagTxt0;
   sc_Bind_c.sendChange(this, "headStartTagTxt0", _headStartTagTxt0);
}

_c._sc_html_tag_HtmlPage_headInit = function() {
   this.setParentNode(this._outer1);
   this.setId(this.allocUniqueId("head"));
   this.serverContent = true;
   this.setHeadStartTagTxt0((sc_Bind_c.arith(this, "headStartTagTxt0", "+", [ sc_Bind_c.arithP("+", [ new sc_ConstantBinding("<head id='"), sc_Bind_c.methodP(this, sc_DynUtil_c.resolveMethod(this.getClass(), "getId", ""), [  ]) ]), new sc_ConstantBinding("'>") ], sc_clInit(sc_BindingDirection_c).FORWARD)));
   sc_Bind_c.method(this, "headStartTagTxt0", this, sc_DynUtil_c.resolveMethod(this.getClass(), "invalidateStartTag", ""), [  ], sc_clInit(sc_BindingDirection_c).REVERSE);
}

// Generated JS from Java: sc.html.tag.HtmlPage.body -----
function sc_html_tag_HtmlPage_body(_outer) {
   this._outer1 = _outer;
   if (this.outer === undefined) this.outer = _outer;
   this.bodyStartTagTxt0 = null;

   js_Body.call(this);
   this._sc_html_tag_HtmlPage_bodyInit();
}

var _c, sc_html_tag_HtmlPage_body_c = _c = sc_newInnerObj("sc.html.tag.HtmlPage.body", sc_html_tag_HtmlPage_body, sc_html_tag_HtmlPage, js_Body, null);

_c._A_TypeSettings = {objectType: true};

_c.outputStartTag = function (out)  {
   out.append(this.getBodyStartTagTxt0());
}
_c.outputBody = function (out)  {
   out.append("\n   ");
}
_c.getBodyStartTagTxt0 = function ()  {
   return this.bodyStartTagTxt0;
}
_c.setBodyStartTagTxt0 = function (_bodyStartTagTxt0)  {
   this.bodyStartTagTxt0 = _bodyStartTagTxt0;
   sc_Bind_c.sendChange(this, "bodyStartTagTxt0", _bodyStartTagTxt0);
}

_c._sc_html_tag_HtmlPage_bodyInit = function() {
   this.setParentNode(this._outer1);
   this.setId(this.allocUniqueId("body"));
   this.setBodyStartTagTxt0((sc_Bind_c.arith(this, "bodyStartTagTxt0", "+", [ sc_Bind_c.arithP("+", [ new sc_ConstantBinding("<body id='"), sc_Bind_c.methodP(this, sc_DynUtil_c.resolveMethod(this.getClass(), "getId", ""), [  ]) ]), new sc_ConstantBinding("'>") ], sc_clInit(sc_BindingDirection_c).FORWARD)));
   sc_Bind_c.method(this, "bodyStartTagTxt0", this, sc_DynUtil_c.resolveMethod(this.getClass(), "invalidateStartTag", ""), [  ], sc_clInit(sc_BindingDirection_c).REVERSE);
}

// Generated JS from Java: sc.html.tag.HtmlPage.script_Repeat -----
function sc_html_tag_HtmlPage_script_Repeat(_outer) {
   sc_clInit(js_IRepeatWrapper_c);
   this._outer1 = _outer;
   if (this.outer === undefined) this.outer = _outer;

   js_HTMLElement.call(this);
   this._sc_html_tag_HtmlPage_script_RepeatInit();
}

var _c, sc_html_tag_HtmlPage_script_Repeat_c = _c = sc_newInnerObj("sc.html.tag.HtmlPage.script_Repeat", sc_html_tag_HtmlPage_script_Repeat, sc_html_tag_HtmlPage, js_HTMLElement, [js_IRepeatWrapper]);

_c._A_TypeSettings = {objectType: true};

_c.createElement = function (val, ix)  {
   var elem = new sc_html_tag_HtmlPage_script_Repeat_script(this);
   elem.setRepeatVar(val);
   elem.setRepeatIndex(ix);
   return elem;
}

_c._sc_html_tag_HtmlPage_script_RepeatInit = function() {
   this.setParentNode(this._outer1);
   this.setRepeat(this.getJSFiles());
}

// Generated JS from Java: sc.html.tag.HtmlPage.script_Repeat.script -----
function sc_html_tag_HtmlPage_script_Repeat_script(_outer) {
   this._outer2 = _outer;
   if (this.outer === undefined) this.outer = _outer;
   this.scriptStartTagTxt0 = null;

   js_HTMLElement.call(this);
   this._sc_html_tag_HtmlPage_script_Repeat_scriptInit();
}

var _c, sc_html_tag_HtmlPage_script_Repeat_script_c = _c = sc_newInnerClass("sc.html.tag.HtmlPage.script_Repeat.script", sc_html_tag_HtmlPage_script_Repeat_script, sc_html_tag_HtmlPage_script_Repeat, js_HTMLElement, null);

_c.outputStartTag = function (out)  {
   out.append(this.getScriptStartTagTxt0());
}
_c.getScriptStartTagTxt0 = function ()  {
   return this.scriptStartTagTxt0;
}
_c.setScriptStartTagTxt0 = function (_scriptStartTagTxt0)  {
   this.scriptStartTagTxt0 = _scriptStartTagTxt0;
   sc_Bind_c.sendChange(this, "scriptStartTagTxt0", _scriptStartTagTxt0);
}

_c._sc_html_tag_HtmlPage_script_Repeat_scriptInit = function() {
   this.setParentNode(this._outer2);
   this.setId(this.allocUniqueId("script"));
   this.tagName = "script";
   this.setScriptStartTagTxt0((sc_Bind_c.arith(this, "scriptStartTagTxt0", "+", [ sc_Bind_c.arithP("+", [ sc_Bind_c.arithP("+", [ sc_Bind_c.arithP("+", [ new sc_ConstantBinding("<script type='text/javascript' src=\""), sc_Bind_c.methodP(this, sc_DynUtil_c.resolveMethod(this.getClass(), "escAtt", "Ljava/lang/CharSequence;"), [ sc_Bind_c.bindP(this, [ "repeatVar" ]) ]) ]), new sc_ConstantBinding("\" id='") ]), sc_Bind_c.methodP(this, sc_DynUtil_c.resolveMethod(this.getClass(), "getId", ""), [  ]) ]), new sc_ConstantBinding("'>") ], sc_clInit(sc_BindingDirection_c).FORWARD)));
   sc_Bind_c.method(this, "scriptStartTagTxt0", this, sc_DynUtil_c.resolveMethod(this.getClass(), "invalidateStartTag", ""), [  ], sc_clInit(sc_BindingDirection_c).REVERSE);
}

// Generated JS from Java: TodoList -----
function TodoList() {   this.todos = null;
   this.todoText = null;

   sc_html_tag_HtmlPage.call(this);
   this._TodoListInit();
}

var _c, TodoList_c = _c = sc_newClass("TodoList", TodoList, sc_html_tag_HtmlPage, null);

_c.getObjChildren = function (create)  {
   if (arguments.length == 0) return;
   if (create) {
      return[ this.getHead(), this.getBody(), this.getScript_Repeat() ];
   }
   else {
      return[ (this.head), (this.body), (this.script_Repeat) ];
   }
}
_c.outputBody = function (out)  {
   sc_html_tag_HtmlPage_c.outputBody.call(this, out);
   out.append("\n  \n  \n");
}
_c.outputStartTag = function (out)  {
   out.append("<!DOCTYPE html>");
   out.append("<html>");
}
_c.getTodos = function ()  {
   return this.todos;
}
_c.setTodos = function (_todos)  {
   this.todos = _todos;
   sc_Bind_c.sendChange(this, "todos", _todos);
}
_c.getTodoText = function ()  {
   return this.todoText;
}
_c.setTodoText = function (_todoText)  {
   this.todoText = _todoText;
   sc_Bind_c.sendChange(this, "todoText", _todoText);
}
_c.addTodoEntry = function ()  {
   this.getTodos().add(this.newTodoItem(this.getTodoText(), false));
   this.setTodoText("");
}
_c.getRemaining = function (todoList)  {
   if (arguments.length == 0) return;
   var count = 0;
   if (todoList === null) {
      jv_System_c.out.println("*** no list");
      return 0;
   }
   for (var _i = todoList.iterator(); _i.hasNext();) {
      var todo = _i.next();
      if (!todo.getComplete())
         count++;
   }
   return count;
}
_c.getSize = function (list)  {
   if (arguments.length == 0) return;
   return list === null ? 0 : list.size();
}
_c.removeComplete = function ()  {
   var newTodos = new sc_ArrayList();
   for (var _i = this.getTodos().iterator(); _i.hasNext();) {
      var todo = _i.next();
      if (!todo.getComplete())
         newTodos.add(todo);
   }
   this.setTodos(newTodos);
}
_c.newTodoItem = function () /* overloaded */ {
   if (arguments.length == 3) {
      var doInit = arguments[0];
      var t = arguments[1];
      var c = arguments[2];
      var todoItem = new TodoList_TodoItem(this, t, c);
      todoItem.preInit();
      sc_DynUtil_c.addDynInnerInstance("TodoList.TodoItem", todoItem, this);
      if (doInit) {
         todoItem.init();
         todoItem.start();
      }
      return todoItem;
   }
   else if (arguments.length == 2) {
      var t = arguments[0];
      var c = arguments[1];
      return this.newTodoItem(true, t, c);
   }
}
_c.getHead = function ()  {
   if (this.head === null) {
      var _head = new TodoList_head(this);
      this.head = _head;
      sc_DynUtil_c.addDynInnerObject("TodoList.head", _head, this);
      return _head;
   }
   else
      return(this.head);
}
_c.getBody = function ()  {
   if (this.body === null) {
      var _body = new TodoList_body(this);
      this.body = _body;
      sc_DynUtil_c.addDynInnerObject("TodoList.body", _body, this);
      return _body;
   }
   else
      return(this.body);
}

_c._TodoListInit = function() {
   this.setTodos(new sc_ArrayList(jv_Arrays_c.asList([ this.newTodoItem("Run layerCake todo sample", true), this.newTodoItem("Check me and see it stay in sync", false), this.newTodoItem("Add a new entry and press 'remove completed'", false) ])));
   this.setTodoText("");
   this.needsRefresh = true;
}

// Generated JS from Java: TodoList.TodoItem -----
function TodoList_TodoItem(_outer, t, c) {
   sc_clInit(sc_IComponent_c);
   this._outer1 = _outer;
   if (this.outer === undefined) this.outer = _outer;
   this.text = null;
   this.complete = false;
   this._initState = 0;


   jv_Object.call(this);
   this._TodoList_TodoItemInit();
   this.setText(t);
   this.setComplete(c);
}

var _c, TodoList_TodoItem_c = _c = sc_newInnerClass("TodoList.TodoItem", TodoList_TodoItem, TodoList, jv_Object, [sc_IComponent]);

_c.getText = function ()  {
   return this.text;
}
_c.setText = function (_text)  {
   this.text = _text;
   sc_Bind_c.sendChange(this, "text", _text);
}
_c.getComplete = function ()  {
   return this.complete;
}
_c.setComplete = function (_complete)  {
   this.complete = _complete;
   sc_Bind_c.sendChange(this, "complete", _complete);
}
_c.getInitState = function ()  {
   return this._initState;
}
_c.preInit = function ()  {
   if (this._initState > 0)
      return;
   this._initState = 1;
}
_c.init = function ()  {
   if (this._initState > 1)
      return;
   this._initState = 2;
}
_c.start = function ()  {
   if (this._initState > 2)
      return;
   this._initState = 3;
}
_c.stop = function ()  {
   if (this._initState > 3)
      return;
   this._initState = 4;
}

_c._TodoList_TodoItemInit = function() {
}

// Generated JS from Java: TodoList.head -----
function TodoList_head(_outer) {
   this._outer1 = _outer;
   if (this.outer === undefined) this.outer = _outer;

   sc_html_tag_HtmlPage_head.call(this, this._outer1);
   this._TodoList_headInit();
}

var _c, TodoList_head_c = _c = sc_newInnerObj("TodoList.head", TodoList_head, TodoList, sc_html_tag_HtmlPage_head, null);

_c._A_TypeSettings = {objectType: true};

_c.outputStartTag = function (out)  {
   out.append(this.getHeadStartTagTxt0());
}
_c.outputBody = function (out)  {
   sc_html_tag_HtmlPage_head_c.outputBody.call(this, out);
   out.append("\n      <link rel='stylesheet' type='text/css' href='todoStyle.css'>\n  ");
}

_c._TodoList_headInit = function() {
   this.setParentNode(this._outer1);
   this.setHeadStartTagTxt0((sc_Bind_c.arith(this, "headStartTagTxt0", "+", [ sc_Bind_c.arithP("+", [ new sc_ConstantBinding("<head id='"), sc_Bind_c.methodP(this, sc_DynUtil_c.resolveMethod(this.getClass(), "getId", ""), [  ]) ]), new sc_ConstantBinding("'>") ], sc_clInit(sc_BindingDirection_c).FORWARD)));
}

// Generated JS from Java: TodoList.body -----
function TodoList_body(_outer) {
   this._outer1 = _outer;
   if (this.outer === undefined) this.outer = _outer;
   this.div = null;

   sc_html_tag_HtmlPage_body.call(this, this._outer1);
   this._TodoList_bodyInit();
}

var _c, TodoList_body_c = _c = sc_newInnerObj("TodoList.body", TodoList_body, TodoList, sc_html_tag_HtmlPage_body, null);

_c._A_TypeSettings = {objectType: true};

_c.getObjChildren = function (create)  {
   if (arguments.length == 0) return;
   if (create) {
      return[ this.getDiv() ];
   }
   else {
      return[ (this.div) ];
   }
}
_c.outputStartTag = function (out)  {
   out.append(this.getBodyStartTagTxt0());
}
_c.outputBody = function (out)  {
   sc_html_tag_HtmlPage_body_c.outputBody.call(this, out);
   out.append("\n    ");
   this.getDiv().outputTag(out);
   out.append("\n  ");
}
_c.getDiv = function ()  {
   if (this.div === null) {
      var _div = new TodoList_body_div(this);
      this.div = _div;
      sc_DynUtil_c.addDynInnerObject("TodoList.body.div", _div, this);
      return _div;
   }
   else
      return this.div;
}

_c._TodoList_bodyInit = function() {
   this.setParentNode(this._outer1);
   this.setBodyStartTagTxt0((sc_Bind_c.arith(this, "bodyStartTagTxt0", "+", [ sc_Bind_c.arithP("+", [ new sc_ConstantBinding("<body id='"), sc_Bind_c.methodP(this, sc_DynUtil_c.resolveMethod(this.getClass(), "getId", ""), [  ]) ]), new sc_ConstantBinding("'>") ], sc_clInit(sc_BindingDirection_c).FORWARD)));
}

// Generated JS from Java: TodoList.body.div -----
function TodoList_body_div(_outer) {
   this._outer2 = _outer;
   if (this.outer === undefined) this.outer = _outer;
   this.divStartTagTxt0 = null;
   this.todoControl = null;

   js_Div.call(this);
   this._TodoList_body_divInit();
}

var _c, TodoList_body_div_c = _c = sc_newInnerObj("TodoList.body.div", TodoList_body_div, TodoList_body, js_Div, null);

_c._A_TypeSettings = {objectType: true};

_c.getObjChildren = function (create)  {
   if (arguments.length == 0) return;
   if (create) {
      return[ this.getTodoControl() ];
   }
   else {
      return[ (this.todoControl) ];
   }
}
_c.outputStartTag = function (out)  {
   out.append(this.getDivStartTagTxt0());
}
_c.outputBody = function (out)  {
   out.append("\n       ");
   out.append("<h2>Todo List</h2>\n       ");
   this.getTodoControl().outputTag(out);
   out.append("\n     ");
}
_c.getDivStartTagTxt0 = function ()  {
   return this.divStartTagTxt0;
}
_c.setDivStartTagTxt0 = function (_divStartTagTxt0)  {
   this.divStartTagTxt0 = _divStartTagTxt0;
   sc_Bind_c.sendChange(this, "divStartTagTxt0", _divStartTagTxt0);
}
_c.getTodoControl = function ()  {
   if (this.todoControl === null) {
      var _todoControl = new TodoList_body_div_todoControl(this);
      this.todoControl = _todoControl;
      sc_DynUtil_c.addDynInnerObject("TodoList.body.div.todoControl", _todoControl, this);
      return _todoControl;
   }
   else
      return this.todoControl;
}

_c._TodoList_body_divInit = function() {
   this.setParentNode(this._outer2);
   this.setId(this.allocUniqueId("div"));
   this.setHTMLClass("appFrame");
   this.setDivStartTagTxt0((sc_Bind_c.arith(this, "divStartTagTxt0", "+", [ sc_Bind_c.arithP("+", [ new sc_ConstantBinding("<div class='appFrame' id='"), sc_Bind_c.methodP(this, sc_DynUtil_c.resolveMethod(this.getClass(), "getId", ""), [  ]) ]), new sc_ConstantBinding("'>") ], sc_clInit(sc_BindingDirection_c).FORWARD)));
   sc_Bind_c.method(this, "divStartTagTxt0", this, sc_DynUtil_c.resolveMethod(this.getClass(), "invalidateStartTag", ""), [  ], sc_clInit(sc_BindingDirection_c).REVERSE);
}

// Generated JS from Java: TodoList.body.div.todoControl -----
function TodoList_body_div_todoControl(_outer) {
   this._outer3 = _outer;
   if (this.outer === undefined) this.outer = _outer;
   this.todoControlStartTagTxt0 = null;
   this.span = null;
   this.a = null;
   this.ulli_Repeat = null;
   this.form = null;

   js_Div.call(this);
   this._TodoList_body_div_todoControlInit();
}

var _c, TodoList_body_div_todoControl_c = _c = sc_newInnerObj("TodoList.body.div.todoControl", TodoList_body_div_todoControl, TodoList_body_div, js_Div, null);

_c._A_TypeSettings = {objectType: true};

_c.getObjChildren = function (create)  {
   if (arguments.length == 0) return;
   if (create) {
      return[ this.getSpan(), this.getA(), this.getUlli_Repeat(), this.getForm() ];
   }
   else {
      return[ (this.span), (this.a), (this.ulli_Repeat), (this.form) ];
   }
}
_c.outputStartTag = function (out)  {
   out.append(this.getTodoControlStartTagTxt0());
}
_c.outputBody = function (out)  {
   out.append("\n         ");
   out.append("\n         ");
   this.getSpan().outputTag(out);
   out.append("\n         \n         [ ");
   this.getA().outputTag(out);
   out.append(" ]\n         <ul>\n           \n           ");
   this.getUlli_Repeat().outputTag(out);
   out.append("\n         </ul>\n         ");
   this.getForm().outputTag(out);
   out.append("\n       ");
}
_c.getTodoControlStartTagTxt0 = function ()  {
   return this.todoControlStartTagTxt0;
}
_c.setTodoControlStartTagTxt0 = function (_todoControlStartTagTxt0)  {
   this.todoControlStartTagTxt0 = _todoControlStartTagTxt0;
   sc_Bind_c.sendChange(this, "todoControlStartTagTxt0", _todoControlStartTagTxt0);
}
_c.getSpan = function ()  {
   if (this.span === null) {
      var _span = new TodoList_body_div_todoControl_span(this);
      this.span = _span;
      sc_DynUtil_c.addDynInnerObject("TodoList.body.div.todoControl.span", _span, this);
      return _span;
   }
   else
      return this.span;
}
_c.getA = function ()  {
   if (this.a === null) {
      var _a = new TodoList_body_div_todoControl_a(this);
      this.a = _a;
      sc_DynUtil_c.addDynInnerObject("TodoList.body.div.todoControl.a", _a, this);
      return _a;
   }
   else
      return this.a;
}
_c.getUlli_Repeat = function ()  {
   if (this.ulli_Repeat === null) {
      var _ulli_Repeat = new TodoList_body_div_todoControl_ulli_Repeat(this);
      this.ulli_Repeat = _ulli_Repeat;
      sc_DynUtil_c.addDynInnerObject("TodoList.body.div.todoControl.ulli_Repeat", _ulli_Repeat, this);
      return _ulli_Repeat;
   }
   else
      return this.ulli_Repeat;
}
_c.getForm = function ()  {
   if (this.form === null) {
      var _form = new TodoList_body_div_todoControl_form(this);
      this.form = _form;
      sc_DynUtil_c.addDynInnerObject("TodoList.body.div.todoControl.form", _form, this);
      return _form;
   }
   else
      return this.form;
}

_c._TodoList_body_div_todoControlInit = function() {
   this.setParentNode(this._outer3);
   this.setId(this.allocUniqueId("todoControl"));
   this.setTodoControlStartTagTxt0((sc_Bind_c.arith(this, "todoControlStartTagTxt0", "+", [ sc_Bind_c.arithP("+", [ new sc_ConstantBinding("<div id='"), sc_Bind_c.methodP(this, sc_DynUtil_c.resolveMethod(this.getClass(), "getId", ""), [  ]) ]), new sc_ConstantBinding("'>") ], sc_clInit(sc_BindingDirection_c).FORWARD)));
   sc_Bind_c.method(this, "todoControlStartTagTxt0", this, sc_DynUtil_c.resolveMethod(this.getClass(), "invalidateStartTag", ""), [  ], sc_clInit(sc_BindingDirection_c).REVERSE);
}

// Generated JS from Java: TodoList.body.div.todoControl.span -----
function TodoList_body_div_todoControl_span(_outer) {
   this._outer4 = _outer;
   if (this.outer === undefined) this.outer = _outer;
   this.spanStartTagTxt0 = null;
   this.spanBody1 = null;
   this.spanBody2 = null;

   js_Span.call(this);
   this._TodoList_body_div_todoControl_spanInit();
}

var _c, TodoList_body_div_todoControl_span_c = _c = sc_newInnerObj("TodoList.body.div.todoControl.span", TodoList_body_div_todoControl_span, TodoList_body_div_todoControl, js_Span, null);

_c._A_TypeSettings = {objectType: true};

_c.outputStartTag = function (out)  {
   out.append(this.getSpanStartTagTxt0());
}
_c.outputBody = function (out)  {
   out.append(this.getSpanBody1());
   out.append(" of ");
   out.append(this.getSpanBody2());
   out.append(" to do");
}
_c.getSpanStartTagTxt0 = function ()  {
   return this.spanStartTagTxt0;
}
_c.setSpanStartTagTxt0 = function (_spanStartTagTxt0)  {
   this.spanStartTagTxt0 = _spanStartTagTxt0;
   sc_Bind_c.sendChange(this, "spanStartTagTxt0", _spanStartTagTxt0);
}
_c.getSpanBody1 = function ()  {
   return this.spanBody1;
}
_c.setSpanBody1 = function (_spanBody1)  {
   this.spanBody1 = _spanBody1;
   sc_Bind_c.sendChange(this, "spanBody1", _spanBody1);
}
_c.getSpanBody2 = function ()  {
   return this.spanBody2;
}
_c.setSpanBody2 = function (_spanBody2)  {
   this.spanBody2 = _spanBody2;
   sc_Bind_c.sendChange(this, "spanBody2", _spanBody2);
}

_c._TodoList_body_div_todoControl_spanInit = function() {
   this.setParentNode(this._outer4);
   this.setId(this.allocUniqueId("span"));
   this.setSpanStartTagTxt0((sc_Bind_c.arith(this, "spanStartTagTxt0", "+", [ sc_Bind_c.arithP("+", [ new sc_ConstantBinding("<span id='"), sc_Bind_c.methodP(this, sc_DynUtil_c.resolveMethod(this.getClass(), "getId", ""), [  ]) ]), new sc_ConstantBinding("'>") ], sc_clInit(sc_BindingDirection_c).FORWARD)));
   sc_Bind_c.method(this, "spanStartTagTxt0", this, sc_DynUtil_c.resolveMethod(this.getClass(), "invalidateStartTag", ""), [  ], sc_clInit(sc_BindingDirection_c).REVERSE);
   this.setSpanBody1((sc_Bind_c.method(this, "spanBody1", sc_DynUtil_c.resolveStaticMethod(String_c, "_valueOf", "I"), [ sc_Bind_c.methodP(this._outer4._outer3._outer2._outer1, sc_DynUtil_c.resolveMethod(this._outer4._outer3._outer2._outer1.getClass(), "getRemaining", "Ljava/util/List;"), [ sc_Bind_c.bindP(this._outer4._outer3._outer2._outer1, [ "todos" ]) ]) ], sc_clInit(sc_BindingDirection_c).FORWARD)));
   sc_Bind_c.method(this, "spanBody1", this, sc_DynUtil_c.resolveMethod(this.getClass(), "invalidateBody", ""), [  ], sc_clInit(sc_BindingDirection_c).REVERSE);
   this.setSpanBody2((sc_Bind_c.method(this, "spanBody2", sc_DynUtil_c.resolveStaticMethod(String_c, "_valueOf", "I"), [ sc_Bind_c.methodP(this._outer4._outer3._outer2._outer1, sc_DynUtil_c.resolveMethod(this._outer4._outer3._outer2._outer1.getClass(), "getSize", "Ljava/util/List;"), [ sc_Bind_c.bindP(this._outer4._outer3._outer2._outer1, [ "todos" ]) ]) ], sc_clInit(sc_BindingDirection_c).FORWARD)));
   sc_Bind_c.method(this, "spanBody2", this, sc_DynUtil_c.resolveMethod(this.getClass(), "invalidateBody", ""), [  ], sc_clInit(sc_BindingDirection_c).REVERSE);
}

// Generated JS from Java: TodoList.body.div.todoControl.a -----
function TodoList_body_div_todoControl_a(_outer) {
   this._outer4 = _outer;
   if (this.outer === undefined) this.outer = _outer;
   this.aStartTagTxt0 = null;

   js_A.call(this);
   this._TodoList_body_div_todoControl_aInit();
}

var _c, TodoList_body_div_todoControl_a_c = _c = sc_newInnerObj("TodoList.body.div.todoControl.a", TodoList_body_div_todoControl_a, TodoList_body_div_todoControl, js_A, null);

_c._A_TypeSettings = {objectType: true};

_c.outputBody = function (out)  {
   out.append("remove completed");
}
_c.outputStartTag = function (out)  {
   out.append(this.getaStartTagTxt0());
}
_c.getaStartTagTxt0 = function ()  {
   return this.aStartTagTxt0;
}
_c.setaStartTagTxt0 = function (_aStartTagTxt0)  {
   this.aStartTagTxt0 = _aStartTagTxt0;
   sc_Bind_c.sendChange(this, "aStartTagTxt0", _aStartTagTxt0);
}

_c._TodoList_body_div_todoControl_aInit = function() {
   this.setParentNode(this._outer4);
   this.setId(this.allocUniqueId("a"));
   sc_Bind_c.method(this, "clickEvent", this._outer4._outer3._outer2._outer1, sc_DynUtil_c.resolveMethod(this._outer4._outer3._outer2._outer1.getClass(), "removeComplete", ""), [  ], sc_clInit(sc_BindingDirection_c).REVERSE);
   this.setaStartTagTxt0((sc_Bind_c.arith(this, "aStartTagTxt0", "+", [ sc_Bind_c.arithP("+", [ new sc_ConstantBinding("<a href='#' onclick=\"return false;\" id='"), sc_Bind_c.methodP(this, sc_DynUtil_c.resolveMethod(this.getClass(), "getId", ""), [  ]) ]), new sc_ConstantBinding("'>") ], sc_clInit(sc_BindingDirection_c).FORWARD)));
   sc_Bind_c.method(this, "aStartTagTxt0", this, sc_DynUtil_c.resolveMethod(this.getClass(), "invalidateStartTag", ""), [  ], sc_clInit(sc_BindingDirection_c).REVERSE);
}

// Generated JS from Java: TodoList.body.div.todoControl.ulli_Repeat -----
function TodoList_body_div_todoControl_ulli_Repeat(_outer) {
   sc_clInit(js_IRepeatWrapper_c);
   this._outer4 = _outer;
   if (this.outer === undefined) this.outer = _outer;

   js_HTMLElement.call(this);
   this._TodoList_body_div_todoControl_ulli_RepeatInit();
}

var _c, TodoList_body_div_todoControl_ulli_Repeat_c = _c = sc_newInnerObj("TodoList.body.div.todoControl.ulli_Repeat", TodoList_body_div_todoControl_ulli_Repeat, TodoList_body_div_todoControl, js_HTMLElement, [js_IRepeatWrapper]);

_c._A_TypeSettings = {objectType: true};

_c.createElement = function (val, ix)  {
   var elem = new TodoList_body_div_todoControl_ulli_Repeat_ulli(this);
   elem.setRepeatVar(val);
   elem.setRepeatIndex(ix);
   return elem;
}

_c._TodoList_body_div_todoControl_ulli_RepeatInit = function() {
   this.setParentNode(this._outer4);
   this.setRepeat((sc_Bind_c.bind(this, "repeat", this._outer4._outer3._outer2._outer1, [ "todos" ], sc_clInit(sc_BindingDirection_c).FORWARD)));
}

// Generated JS from Java: TodoList.body.div.todoControl.ulli_Repeat.ulli -----
function TodoList_body_div_todoControl_ulli_Repeat_ulli(_outer) {
   this._outer5 = _outer;
   if (this.outer === undefined) this.outer = _outer;
   this.todo = null;
   this.ulliStartTagTxt0 = null;
   this.input = null;
   this.span1 = null;

   js_HTMLElement.call(this);
   this._TodoList_body_div_todoControl_ulli_Repeat_ulliInit();
}

var _c, TodoList_body_div_todoControl_ulli_Repeat_ulli_c = _c = sc_newInnerClass("TodoList.body.div.todoControl.ulli_Repeat.ulli", TodoList_body_div_todoControl_ulli_Repeat_ulli, TodoList_body_div_todoControl_ulli_Repeat, js_HTMLElement, null);

_c.outputStartTag = function (out)  {
   out.append(this.getUlliStartTagTxt0());
}
_c.getObjChildren = function (create)  {
   if (arguments.length == 0) return;
   if (create) {
      return[ this.getInput(), this.getSpan1() ];
   }
   else {
      return[ (this.input), (this.span1) ];
   }
}
_c.outputBody = function (out)  {
   out.append("\n             ");
   out.append("\n             ");
   this.getInput().outputTag(out);
   out.append("\n             \n             ");
   this.getSpan1().outputTag(out);
   out.append("\n           ");
}
_c.getTodo = function ()  {
   return this.todo;
}
_c.setTodo = function (_todo)  {
   this.todo = _todo;
   sc_Bind_c.sendChange(this, "todo", _todo);
}
_c.getUlliStartTagTxt0 = function ()  {
   return this.ulliStartTagTxt0;
}
_c.setUlliStartTagTxt0 = function (_ulliStartTagTxt0)  {
   this.ulliStartTagTxt0 = _ulliStartTagTxt0;
   sc_Bind_c.sendChange(this, "ulliStartTagTxt0", _ulliStartTagTxt0);
}
_c.getInput = function ()  {
   if (this.input === null) {
      var _input = new TodoList_body_div_todoControl_ulli_Repeat_ulli_input(this);
      this.input = _input;
      sc_DynUtil_c.addDynInnerObject("TodoList.body.div.todoControl.ulli_Repeat.ulli.input", _input, this);
      return _input;
   }
   else
      return this.input;
}
_c.getSpan1 = function ()  {
   if (this.span1 === null) {
      var _span1 = new TodoList_body_div_todoControl_ulli_Repeat_ulli_span1(this);
      this.span1 = _span1;
      sc_DynUtil_c.addDynInnerObject("TodoList.body.div.todoControl.ulli_Repeat.ulli.span1", _span1, this);
      return _span1;
   }
   else
      return this.span1;
}

_c._TodoList_body_div_todoControl_ulli_Repeat_ulliInit = function() {
   this.setParentNode(this._outer5);
   this.setId(this.allocUniqueId("ulli"));
   this.tagName = "li";
   this.setRepeatVarName("todo");
   this.setTodo((sc_Bind_c.cast(this, "todo", TodoList_TodoItem_c, sc_Bind_c.bindP(this, [ "repeatVar" ]), sc_clInit(sc_BindingDirection_c).BIDIRECTIONAL)));
   this.setUlliStartTagTxt0((sc_Bind_c.arith(this, "ulliStartTagTxt0", "+", [ sc_Bind_c.arithP("+", [ new sc_ConstantBinding("<li id='"), sc_Bind_c.methodP(this, sc_DynUtil_c.resolveMethod(this.getClass(), "getId", ""), [  ]) ]), new sc_ConstantBinding("'>") ], sc_clInit(sc_BindingDirection_c).FORWARD)));
   sc_Bind_c.method(this, "ulliStartTagTxt0", this, sc_DynUtil_c.resolveMethod(this.getClass(), "invalidateStartTag", ""), [  ], sc_clInit(sc_BindingDirection_c).REVERSE);
}

// Generated JS from Java: TodoList.body.div.todoControl.ulli_Repeat.ulli.input -----
function TodoList_body_div_todoControl_ulli_Repeat_ulli_input(_outer) {
   this._outer6 = _outer;
   if (this.outer === undefined) this.outer = _outer;
   this.inputStartTagTxt0 = null;

   js_Input.call(this);
   this._TodoList_body_div_todoControl_ulli_Repeat_ulli_inputInit();
}

var _c, TodoList_body_div_todoControl_ulli_Repeat_ulli_input_c = _c = sc_newInnerObj("TodoList.body.div.todoControl.ulli_Repeat.ulli.input", TodoList_body_div_todoControl_ulli_Repeat_ulli_input, TodoList_body_div_todoControl_ulli_Repeat_ulli, js_Input, null);

_c._A_TypeSettings = {objectType: true};

_c.outputStartTag = function (out)  {
   out.append(this.getInputStartTagTxt0());
}
_c.getInputStartTagTxt0 = function ()  {
   return this.inputStartTagTxt0;
}
_c.setInputStartTagTxt0 = function (_inputStartTagTxt0)  {
   this.inputStartTagTxt0 = _inputStartTagTxt0;
   sc_Bind_c.sendChange(this, "inputStartTagTxt0", _inputStartTagTxt0);
}

_c._TodoList_body_div_todoControl_ulli_Repeat_ulli_inputInit = function() {
   this.setParentNode(this._outer6);
   this.setId(this.allocUniqueId("input"));
   this.type = "checkbox";
   this.setChecked(sc_DynUtil_c.booleanValue(sc_Bind_c.bind(this, "checked", this._outer6, [ "todo", "complete" ], sc_clInit(sc_BindingDirection_c).BIDIRECTIONAL)));
   this.setInputStartTagTxt0((sc_Bind_c.arith(this, "inputStartTagTxt0", "+", [ sc_Bind_c.arithP("+", [ sc_Bind_c.arithP("+", [ sc_Bind_c.arithP("+", [ new sc_ConstantBinding("<input type='checkbox'"), (sc_Bind_c.ternaryP([ sc_Bind_c.bindP(this._outer6, [ "todo", "complete" ]), new sc_ConstantBinding(" checked"), new sc_ConstantBinding("") ])) ]), new sc_ConstantBinding(" id='") ]), sc_Bind_c.methodP(this, sc_DynUtil_c.resolveMethod(this.getClass(), "getId", ""), [  ]) ]), new sc_ConstantBinding("'>") ], sc_clInit(sc_BindingDirection_c).FORWARD)));
   sc_Bind_c.method(this, "inputStartTagTxt0", this, sc_DynUtil_c.resolveMethod(this.getClass(), "invalidateStartTag", ""), [  ], sc_clInit(sc_BindingDirection_c).REVERSE);
}

// Generated JS from Java: TodoList.body.div.todoControl.ulli_Repeat.ulli.span1 -----
function TodoList_body_div_todoControl_ulli_Repeat_ulli_span1(_outer) {
   this._outer6 = _outer;
   if (this.outer === undefined) this.outer = _outer;
   this.span1StartTagTxt0 = null;
   this.span1Body1 = null;

   js_Span.call(this);
   this._TodoList_body_div_todoControl_ulli_Repeat_ulli_span1Init();
}

var _c, TodoList_body_div_todoControl_ulli_Repeat_ulli_span1_c = _c = sc_newInnerObj("TodoList.body.div.todoControl.ulli_Repeat.ulli.span1", TodoList_body_div_todoControl_ulli_Repeat_ulli_span1, TodoList_body_div_todoControl_ulli_Repeat_ulli, js_Span, null);

_c._A_TypeSettings = {objectType: true};

_c.outputStartTag = function (out)  {
   out.append(this.getSpan1StartTagTxt0());
}
_c.outputBody = function (out)  {
   out.append(this.getSpan1Body1());
}
_c.getSpan1StartTagTxt0 = function ()  {
   return this.span1StartTagTxt0;
}
_c.setSpan1StartTagTxt0 = function (_span1StartTagTxt0)  {
   this.span1StartTagTxt0 = _span1StartTagTxt0;
   sc_Bind_c.sendChange(this, "span1StartTagTxt0", _span1StartTagTxt0);
}
_c.getSpan1Body1 = function ()  {
   return this.span1Body1;
}
_c.setSpan1Body1 = function (_span1Body1)  {
   this.span1Body1 = _span1Body1;
   sc_Bind_c.sendChange(this, "span1Body1", _span1Body1);
}

_c._TodoList_body_div_todoControl_ulli_Repeat_ulli_span1Init = function() {
   this.setParentNode(this._outer6);
   this.setId(this.allocUniqueId("span1"));
   this.setHTMLClass((sc_Bind_c.arith(this, "HTMLClass", "+", [ new sc_ConstantBinding("complete-"), sc_Bind_c.bindP(this._outer6, [ "todo", "complete" ]) ], sc_clInit(sc_BindingDirection_c).FORWARD)));
   this.setSpan1StartTagTxt0((sc_Bind_c.arith(this, "span1StartTagTxt0", "+", [ sc_Bind_c.arithP("+", [ sc_Bind_c.arithP("+", [ sc_Bind_c.arithP("+", [ new sc_ConstantBinding("<span class=\""), sc_Bind_c.methodP(this, sc_DynUtil_c.resolveMethod(this.getClass(), "escAtt", "Ljava/lang/CharSequence;"), [ sc_Bind_c.arithP("+", [ new sc_ConstantBinding("complete-"), sc_Bind_c.bindP(this._outer6, [ "todo", "complete" ]) ]) ]) ]), new sc_ConstantBinding("\" id='") ]), sc_Bind_c.methodP(this, sc_DynUtil_c.resolveMethod(this.getClass(), "getId", ""), [  ]) ]), new sc_ConstantBinding("'>") ], sc_clInit(sc_BindingDirection_c).FORWARD)));
   sc_Bind_c.method(this, "span1StartTagTxt0", this, sc_DynUtil_c.resolveMethod(this.getClass(), "invalidateStartTag", ""), [  ], sc_clInit(sc_BindingDirection_c).REVERSE);
   this.setSpan1Body1((sc_Bind_c.method(this, "span1Body1", this, sc_DynUtil_c.resolveMethod(this.getClass(), "escBody", "Ljava/lang/Object;"), [ sc_Bind_c.bindP(this._outer6, [ "todo", "text" ]) ], sc_clInit(sc_BindingDirection_c).FORWARD)));
   sc_Bind_c.method(this, "span1Body1", this, sc_DynUtil_c.resolveMethod(this.getClass(), "invalidateBody", ""), [  ], sc_clInit(sc_BindingDirection_c).REVERSE);
}

// Generated JS from Java: TodoList.body.div.todoControl.form -----
function TodoList_body_div_todoControl_form(_outer) {
   this._outer4 = _outer;
   if (this.outer === undefined) this.outer = _outer;
   this.formStartTagTxt0 = null;
   this.input0 = null;
   this.input1 = null;

   js_Form.call(this);
   this._TodoList_body_div_todoControl_formInit();
}

var _c, TodoList_body_div_todoControl_form_c = _c = sc_newInnerObj("TodoList.body.div.todoControl.form", TodoList_body_div_todoControl_form, TodoList_body_div_todoControl, js_Form, null);

_c._A_TypeSettings = {objectType: true};

_c.outputStartTag = function (out)  {
   out.append(this.getFormStartTagTxt0());
}
_c.outputBody = function (out)  {
   out.append("\n           ");
   this.getInput0().outputTag(out);
   out.append("\n           ");
   this.getInput1().outputTag(out);
   out.append("\n         ");
}
_c.getObjChildren = function (create)  {
   if (arguments.length == 0) return;
   if (create) {
      return[ this.getInput0(), this.getInput1() ];
   }
   else {
      return[ (this.input0), (this.input1) ];
   }
}
_c.getFormStartTagTxt0 = function ()  {
   return this.formStartTagTxt0;
}
_c.setFormStartTagTxt0 = function (_formStartTagTxt0)  {
   this.formStartTagTxt0 = _formStartTagTxt0;
   sc_Bind_c.sendChange(this, "formStartTagTxt0", _formStartTagTxt0);
}
_c.getInput0 = function ()  {
   if (this.input0 === null) {
      var _input0 = new TodoList_body_div_todoControl_form_input0(this);
      this.input0 = _input0;
      sc_DynUtil_c.addDynInnerObject("TodoList.body.div.todoControl.form.input0", _input0, this);
      return _input0;
   }
   else
      return this.input0;
}
_c.getInput1 = function ()  {
   if (this.input1 === null) {
      var _input1 = new TodoList_body_div_todoControl_form_input1(this);
      this.input1 = _input1;
      sc_DynUtil_c.addDynInnerObject("TodoList.body.div.todoControl.form.input1", _input1, this);
      return _input1;
   }
   else
      return this.input1;
}

_c._TodoList_body_div_todoControl_formInit = function() {
   this.setParentNode(this._outer4);
   this.setId(this.allocUniqueId("form"));
   sc_Bind_c.method(this, "submitEvent", this._outer4._outer3._outer2._outer1, sc_DynUtil_c.resolveMethod(this._outer4._outer3._outer2._outer1.getClass(), "addTodoEntry", ""), [  ], sc_clInit(sc_BindingDirection_c).REVERSE);
   this.setFormStartTagTxt0((sc_Bind_c.arith(this, "formStartTagTxt0", "+", [ sc_Bind_c.arithP("+", [ new sc_ConstantBinding("<form onSubmit=\"return false;\" onsubmit=\"return false;\" id='"), sc_Bind_c.methodP(this, sc_DynUtil_c.resolveMethod(this.getClass(), "getId", ""), [  ]) ]), new sc_ConstantBinding("'>") ], sc_clInit(sc_BindingDirection_c).FORWARD)));
   sc_Bind_c.method(this, "formStartTagTxt0", this, sc_DynUtil_c.resolveMethod(this.getClass(), "invalidateStartTag", ""), [  ], sc_clInit(sc_BindingDirection_c).REVERSE);
}

// Generated JS from Java: TodoList.body.div.todoControl.form.input0 -----
function TodoList_body_div_todoControl_form_input0(_outer) {
   this._outer5 = _outer;
   if (this.outer === undefined) this.outer = _outer;
   this.input0StartTagTxt0 = null;

   js_Input.call(this);
   this._TodoList_body_div_todoControl_form_input0Init();
}

var _c, TodoList_body_div_todoControl_form_input0_c = _c = sc_newInnerObj("TodoList.body.div.todoControl.form.input0", TodoList_body_div_todoControl_form_input0, TodoList_body_div_todoControl_form, js_Input, null);

_c._A_TypeSettings = {objectType: true};

_c.outputStartTag = function (out)  {
   out.append(this.getInput0StartTagTxt0());
}
_c.getInput0StartTagTxt0 = function ()  {
   return this.input0StartTagTxt0;
}
_c.setInput0StartTagTxt0 = function (_input0StartTagTxt0)  {
   this.input0StartTagTxt0 = _input0StartTagTxt0;
   sc_Bind_c.sendChange(this, "input0StartTagTxt0", _input0StartTagTxt0);
}

_c._TodoList_body_div_todoControl_form_input0Init = function() {
   this.setParentNode(this._outer5);
   this.setId(this.allocUniqueId("input0"));
   this.type = "text";
   this.setValue((sc_Bind_c.bind(this, "value", this._outer5._outer4._outer3._outer2._outer1, [ "todoText" ], sc_clInit(sc_BindingDirection_c).BIDIRECTIONAL)));
   this.size = 45;
   this.setInput0StartTagTxt0((sc_Bind_c.arith(this, "input0StartTagTxt0", "+", [ sc_Bind_c.arithP("+", [ sc_Bind_c.arithP("+", [ sc_Bind_c.arithP("+", [ sc_Bind_c.arithP("+", [ sc_Bind_c.arithP("+", [ new sc_ConstantBinding("<input type='text' value=\""), sc_Bind_c.methodP(this, sc_DynUtil_c.resolveMethod(this.getClass(), "escAtt", "Ljava/lang/CharSequence;"), [ sc_Bind_c.bindP(this._outer5._outer4._outer3._outer2._outer1, [ "todoText" ]) ]) ]), new sc_ConstantBinding("\" size=\"") ]), new sc_ConstantBinding(45) ]), new sc_ConstantBinding("\" placeholder='enter todo entry here' id='") ]), sc_Bind_c.methodP(this, sc_DynUtil_c.resolveMethod(this.getClass(), "getId", ""), [  ]) ]), new sc_ConstantBinding("'>") ], sc_clInit(sc_BindingDirection_c).FORWARD)));
   sc_Bind_c.method(this, "input0StartTagTxt0", this, sc_DynUtil_c.resolveMethod(this.getClass(), "invalidateStartTag", ""), [  ], sc_clInit(sc_BindingDirection_c).REVERSE);
}

// Generated JS from Java: TodoList.body.div.todoControl.form.input1 -----
function TodoList_body_div_todoControl_form_input1(_outer) {
   this._outer5 = _outer;
   if (this.outer === undefined) this.outer = _outer;
   this.input1StartTagTxt0 = null;

   js_Input.call(this);
   this._TodoList_body_div_todoControl_form_input1Init();
}

var _c, TodoList_body_div_todoControl_form_input1_c = _c = sc_newInnerObj("TodoList.body.div.todoControl.form.input1", TodoList_body_div_todoControl_form_input1, TodoList_body_div_todoControl_form, js_Input, null);

_c._A_TypeSettings = {objectType: true};

_c.outputStartTag = function (out)  {
   out.append(this.getInput1StartTagTxt0());
}
_c.getInput1StartTagTxt0 = function ()  {
   return this.input1StartTagTxt0;
}
_c.setInput1StartTagTxt0 = function (_input1StartTagTxt0)  {
   this.input1StartTagTxt0 = _input1StartTagTxt0;
   sc_Bind_c.sendChange(this, "input1StartTagTxt0", _input1StartTagTxt0);
}

_c._TodoList_body_div_todoControl_form_input1Init = function() {
   this.setParentNode(this._outer5);
   this.setId(this.allocUniqueId("input1"));
   this.type = "submit";
   this.setValue("Add");
   this.setDisabled(sc_DynUtil_c.booleanValue(sc_Bind_c.condition(this, "disabled", "==", [ sc_Bind_c.methodP(sc_DynUtil_c.resolveStaticMethod(sc_TextUtil_c, "length", "Ljava/lang/String;"), [ sc_Bind_c.bindP(this._outer5._outer4._outer3._outer2._outer1, [ "todoText" ]) ]), new sc_ConstantBinding(0) ], sc_clInit(sc_BindingDirection_c).FORWARD)));
   this.setInput1StartTagTxt0((sc_Bind_c.arith(this, "input1StartTagTxt0", "+", [ sc_Bind_c.arithP("+", [ sc_Bind_c.arithP("+", [ sc_Bind_c.arithP("+", [ new sc_ConstantBinding("<input type='submit' value='Add'"), (sc_Bind_c.ternaryP([ sc_Bind_c.conditionP("==", [ sc_Bind_c.methodP(sc_DynUtil_c.resolveStaticMethod(sc_TextUtil_c, "length", "Ljava/lang/String;"), [ sc_Bind_c.bindP(this._outer5._outer4._outer3._outer2._outer1, [ "todoText" ]) ]), new sc_ConstantBinding(0) ]), new sc_ConstantBinding(" disabled"), new sc_ConstantBinding("") ])) ]), new sc_ConstantBinding(" id='") ]), sc_Bind_c.methodP(this, sc_DynUtil_c.resolveMethod(this.getClass(), "getId", ""), [  ]) ]), new sc_ConstantBinding("'>") ], sc_clInit(sc_BindingDirection_c).FORWARD)));
   sc_Bind_c.method(this, "input1StartTagTxt0", this, sc_DynUtil_c.resolveMethod(this.getClass(), "invalidateStartTag", ""), [  ], sc_clInit(sc_BindingDirection_c).REVERSE);
}

// Generated JS from Java: sc.util.ArrayList -----
function sc_ArrayList() {
   sc_ArrayList_c._clInit();


   if (arguments.length == 1) {
      if (sc_instanceOf(arguments[0], Number)) { 
         var initialCapacity = arguments[0];
         jv_ArrayList.call(this, initialCapacity);
}else if (sc_paramType(arguments[0], jv_Collection)) { 
         var c = arguments[0];
         jv_ArrayList.call(this, c);
}}
   else if (arguments.length == 0) {
      jv_ArrayList.call(this, 10);
}}

var _c, sc_ArrayList_c = _c = sc_newClass("sc.util.ArrayList", sc_ArrayList, jv_ArrayList, [sc_IChangeable]);

_c._A_Sync = {syncMode: sc_clInit(sc_SyncMode_c).Disabled};

_c.add = function () /* overloaded */ {
   if (arguments.length == 1) {
      var value = arguments[0];
      var res = jv_ArrayList_c.add.call(this, value);
      sc_Bind_c.sendEvent(sc_clInit(sc_IListener_c).VALUE_CHANGED, this, null);
      return res;
   }
   else if (arguments.length == 2) {
      var index = arguments[0];
      var element = arguments[1];
      jv_ArrayList_c.add.call(this, index, element);
      sc_Bind_c.sendEvent(sc_clInit(sc_IListener_c).VALUE_CHANGED, this, null);
   }
}
_c.addAll = function () /* overloaded */ {
   if (arguments.length == 1) {
      var c = arguments[0];
      var res = jv_ArrayList_c.addAll.call(this, c);
      sc_Bind_c.sendEvent(sc_clInit(sc_IListener_c).VALUE_CHANGED, this, null);
      return res;
   }
   else if (arguments.length == 2) {
      var index = arguments[0];
      var c = arguments[1];
      var res = jv_ArrayList_c.addAll.call(this, index, c);
      sc_Bind_c.sendEvent(sc_clInit(sc_IListener_c).VALUE_CHANGED, this, null);
      return res;
   }
}
_c.clear = function ()  {
   jv_ArrayList_c.clear.call(this);
   sc_Bind_c.sendEvent(sc_clInit(sc_IListener_c).VALUE_CHANGED, this, null);
}
_c.remove = function () /* overloaded */ {
      if (sc_instanceOf(arguments[0], Number)) { 
      var index = arguments[0];
      var res = jv_ArrayList_c.remove.call(this, index);
      sc_Bind_c.sendEvent(sc_clInit(sc_IListener_c).VALUE_CHANGED, this, null);
      return res;
}
      else if (sc_paramType(arguments[0], jv_Object)) { 
      var o = arguments[0];
      var res = jv_ArrayList_c.remove.call(this, o);
      sc_Bind_c.sendEvent(sc_clInit(sc_IListener_c).VALUE_CHANGED, this, null);
      return res;
}}
_c.removeAll = function (c)  {
   var res = jv_AbstractCollection_c.removeAll.call(this, c);
   sc_Bind_c.sendEvent(sc_clInit(sc_IListener_c).VALUE_CHANGED, this, null);
   return res;
}
_c.retainAll = function (c)  {
   var res = jv_AbstractCollection_c.retainAll.call(this, c);
   sc_Bind_c.sendEvent(sc_clInit(sc_IListener_c).VALUE_CHANGED, this, null);
   return res;
}
_c.set = function (index, element)  {
   var res = jv_ArrayList_c.set.call(this, index, element);
   sc_Bind_c.sendEvent(sc_clInit(sc_IListener_c).VALUE_CHANGED, this, null);
   return res;
}
_c.getNoError = function (index)  {
   if (arguments.length == 0) return;
   if (index >= this.size() || index < 0)
      return null;
   return jv_ArrayList_c.get.call(this, index);
}

_c._clInit = function() {
   if (sc_ArrayList_c.hasOwnProperty("_clInited")) return;
   sc_ArrayList_c._clInited = true;
   
   sc_ArrayList_c._getNoErrorMethBindSettings = new sc_MethodBindSettings(sc_ArrayList_c.set, 1, -1, false, false, false);

}

var _inst;
sc_DynUtil_c.addDynObject("TodoList", _inst = new TodoList());

