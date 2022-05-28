function ProblemPanel(problem) {
    var state;
    var stateCanvas;
    var stateDisplay;
    var movesUL;
    var assistant;
    var messageDisplay;

    this.panel = $("<div></div>");
    this.panel.addClass("outer");

    this.panel.append(welcome());
    this.panel.append(intro());
    this.panel.append(stateArea());
    this.panel.append(movesArea());
    this.panel.append(bottomArea());
    
//    window.win = congratulate;

    /*
     * Make the message and reset button area.
     */
    function bottomArea() {
	messageDisplay = $("<p></p>");
	messageDisplay.addClass("emphasized");

	var resetButton = $("<input></input>");
	resetButton.attr("type", "button");
	resetButton.val("RESET");
	resetButton.click(function() {
	    reset();
	});

	var bottomDiv = $("<div></div>");
	bottomDiv.addClass("bottom centerText largeBold");
	bottomDiv.append(messageDisplay);
	bottomDiv.append(resetButton);

	return bottomDiv;
    }
    
    function reset() {
        clearMessage();
	assistant.reset();
        problem.currentState = problem.initialState;
        updateState();
	movesUL.fadeIn("slow");
    }

    /*
     * Make the moves display area
     */
    function movesArea() {
	var movesDiv = $("<div></div>");
	movesDiv.addClass("right centerText");
	movesDiv.append(boldTextElement("Possible Moves"));

	movesUL = $("<ul></ul>");
	assistant = new SolvingAssistant(problem);
	var moveNames = problem.mover.moveNames;
	const scaler = 0.65; // to get button size right
	var bSize = (scaler * buttonSize(moveNames) * 16) + "px";

	moveNames.forEach((m) => makeButton(m));
	movesDiv.append(movesUL);
	return movesDiv;

	function makeButton(m) {
	    var button = $("<input></input>");

	    button.attr("type", "button");
	    button.val(m);
	    button.css("width", bSize);
	    button.addClass("moveButton");

	    addActionForButton(button, m);

	    var item = $("<li></li>");
	    item.append(button);
	    movesUL.append(item);
	};

	function addActionForButton(button, m) {
	    button.click(function () {
		assistant.tryMove(m);
		if (assistant.moveLegal) {
		    clearMessage();
		    updateState();
		    if (assistant.problemSolved) {
			displayMessage("Congratulations. You solved the problem in "
				       + assistant.moveCount + " moves.");
			movesUL.fadeOut("slow");
//			animateCongrats();
                        congratulate();
		    }
		} else {
		    displayMessage("That move is not legal.");
		}
	    });
	};
    }

    function animateCongrats() {
	messageDisplay.css("font-size", 'xx-small');
	messageDisplay.css("color", 'Green');
	messageDisplay.css("background-color", 'GoldenRod');
	messageDisplay.animate({fontSize: '1.5em'}, "slow");
    }
    
    function congratulate() {
        $("#chooser").hide();
        const congrats = $("<div id='congrats'></div>");
        congrats.css("width", "500px");
        congrats.css("height", "650px");
        congrats.css("background-color", "crimson");
        
        const congratsStateDisplay = $("<div></div>");
	congratsStateDisplay.addClass("center");
	const congratsState = problem.currentState;
	const congratsStateCanvas = congratsState.makeCanvas();
        const displayWidth = congratsStateCanvas.width + 44;
        const displayHeight = congratsStateCanvas.height + 44;
        congratsStateDisplay.css("width", `${displayWidth}px`);
        congratsStateDisplay.css("height", `${displayHeight}px`);
        congratsStateDisplay.css("left", `${(500 - displayWidth) / 2}px`);
	congratsStateDisplay.append($(congratsStateCanvas));
        
        congrats.append(congratsStateDisplay);
        
        const title = $("<h1></h1>");
        title.text("Congratulations!");
        title.addClass("congratsText centerText");
        title.css("top", `${displayHeight + 30}px`);
        title.css("font-size", "50px");
        congrats.append(title);
        
        const message = $("<h1></h1>");
        message.text("You solved the problem using");
        message.addClass("congratsText centerText");
        message.css("top", `${displayHeight + 20}px`);
        message.css("font-size", "35x");
        congrats.append(message);
        
        const moves = $("<h1></h1>");
        moves.text(assistant.moveCount);
        moves.addClass("congratsText centerText");
        moves.css("top", `${displayHeight + 20}px`);
        moves.css("color", "black");
        moves.css("font-size", "50px");
        congrats.append(moves);
        
        const movesText = $("<h1></h1>");
        movesText.text("moves");
        movesText.addClass("congratsText centerText");
        movesText.css("top", `${displayHeight}px`);
        movesText.css("font-size", "35px");
        congrats.append(movesText);
        
        function makeButton(text) {
	    var button = $("<input></input>");

	    button.attr("type", "button");
	    button.val(text);
	    button.addClass("congratsButton");
            
            return button;
	};
        
        const bContinue = makeButton("Continue");
        bContinue.css("left", "120px");
        bContinue.click(() => {
            congrats.empty();
            congrats.hide();
            reset();
            $("#chooser").show();
        });
        congrats.append(bContinue);
        
        const bQuit = makeButton("Quit");
        bQuit.css("left", "300px");
        bQuit.click(() => {
            congrats.empty();
            
            const goodbye = $("<h1></h1>");
            goodbye.text("GOODBYE");
            goodbye.addClass("congratsText centerText");
            goodbye.css("font-size", "40px");
            goodbye.css("color", "aqua");
            goodbye.css("top", "300px");
            
            congrats.append(goodbye);
        });
        congrats.append(bQuit);
        
        $("body").append(congrats);
    }

    /*
     * Change the canvas of the current state display area
     */
    function updateState() {
	$(stateCanvas).remove();
	var prevState = state;
	state = problem.currentState;
	stateCanvas = state.makeCanvas();
	stateDisplay.append($(stateCanvas));
	state.animateMove(prevState);
    }

    /*
     * Display a string to the message display area.
     */
    function displayMessage(str) {
	messageDisplay.text(str);
    }

    /*
     * Clear the message display area.
     */
    function clearMessage() {
	messageDisplay.css("font-size", 'medium'); // restore after
	messageDisplay.css("color", 'FireBrick');  // animation
	messageDisplay.css("background-color", 'transparent');
	displayMessage("");
    }

    /*
     * Compute and return maximum move button label size
     */
    function buttonSize(moveNames) {
	var size = 0;
	moveNames.forEach(function (m) {
	    if (m.length > size) {
		size = m.length;
	    }
	});
	return size;
    }

    /*
     * Make the current state display.
     */
    function stateArea() {
	stateDisplay = $("<div></div>");
	stateDisplay.addClass("left centerText");
	stateDisplay.append(boldTextElement("Current State"));
	state = problem.currentState;
	stateCanvas = state.makeCanvas();
	stateDisplay.append($(stateCanvas));
	return stateDisplay;
    }

    /*
     * Make the introductory text for the problem.
     */
    function intro() {
	var introP = $("<p></p>");
	introP.addClass("justifyText");
	introP.text(problem.introduction);
	return introP;
    }

    /*
     * Make the welcoming text for the problem.
     */
    function welcome() {
	var welcomeDiv = $("<div></div>");
	welcomeDiv.addClass("centerText");
	welcomeDiv.append(boldTextElement("Welcome to the "));

	var problemName = boldTextElement(problem.name);
	problemName.addClass("largeBold emphasized");
	welcomeDiv.append(problemName);

	welcomeDiv.append(boldTextElement(" Problem"));
	return welcomeDiv;
    }

    /*
     * Make and return a text element with a large bold font 
     * for a given string.
     */
    function boldTextElement(str) {
	var e = $("<span></span>");
	e.text(str);
	e.addClass("largeBold");
	return e;
    }

}
