var device = {
    address: 'C2000801AC339F10',
    getValue: function () {
        return delegate.getValue() * 100;
    },
    getLabel: function () {
        return 'decorated ' + delegate.getLabel();
    }
};


